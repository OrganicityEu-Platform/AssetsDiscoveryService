package eu.organicity.discovery.cache;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Caching Aspect.
 */
@Aspect
@Component
public class CachingAspect {

    /**
     * Static logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CachingAspect.class);

    public static final String CACHE_NAME = "oc-";

    /**
     * Connection to REDIS server.
     */
    @Autowired
    private org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate;

    /**
     * Simple constructor.
     */
    public CachingAspect() {
        LOGGER.debug("CachingAspect initialized");
    }

    /**
     * This pointcut matches all methods with a <code>@Cachable</code> annotation.
     */
    @Pointcut("@annotation( thisCachename )")
    @SuppressWarnings("unused")
    private void cache(final Cachable thisCachename) {
        // do nothing
        LOGGER.debug("caching aspect");
    }

    /**
     * Returns objects from the cache if necessary.
     *
     * @param thisJoinPoint the JoinPoint
     * @param thisCachename the Cachename
     * @return the result of the given function
     */
    @Around("cache(thisCachename)")
    public final Object aroundCache(final ProceedingJoinPoint thisJoinPoint,
                                    final Cachable thisCachename)
            throws Throwable {
        try {
            if (thisJoinPoint.getKind().equals(ProceedingJoinPoint.METHOD_CALL)) {
                return thisJoinPoint.proceed();

            } else {
                final long timeStamp = System.currentTimeMillis();

                final String thisJoinPointName = constructCacheKey(thisJoinPoint);
                final String thisJoinPointArgs = getJointPointArgs(thisJoinPoint);
                final String objName = thisJoinPointName + "-" + thisJoinPointArgs;

                final String trueCacheName;
                final String trueCacheKey;
                int expiration = thisCachename.expiration();

                trueCacheName = thisCachename.cacheName();
                trueCacheKey = objName;

                // check if this is an evict operation
                if (thisCachename.evict()) {
                    redisTemplate.delete(CACHE_NAME + trueCacheName);

                    final long timeStampNow = System.currentTimeMillis();
                    LOGGER.info("[" + trueCacheName + "] EVICT "
                            + thisJoinPointName
                            + " ["
                            + thisJoinPointArgs
                            + "] "
                            + (timeStampNow - timeStamp) + " ms");

                    return thisJoinPoint.proceed();

                } else {
                    LOGGER.debug("[" + trueCacheName + "] CACHE LOOKUP "
                            + thisJoinPointName
                            + " ["
                            + thisJoinPointArgs
                            + "] ");

                    //search constant cache for an obj name.
                    final Object cacheContent = redisTemplate.opsForHash().get(CACHE_NAME + trueCacheName, trueCacheKey);
                    if (cacheContent == null) {
                        // compute result of invocation
                        final Object result = thisJoinPoint.proceed();

                        // keep result in cache
                        redisTemplate.opsForHash().put(CACHE_NAME + trueCacheName, trueCacheKey, result);

                        if (expiration > 0) {
                            redisTemplate.expire(CACHE_NAME + trueCacheName, expiration, TimeUnit.MINUTES);
                        }

                        final long timeStampNow = System.currentTimeMillis();
                        LOGGER.info("[" + trueCacheName + "] MISS "
                                + thisJoinPointName
                                + " ["
                                + thisJoinPointArgs
                                + "]{"
                                + expiration
                                + "min} "
                                + (timeStampNow - timeStamp) + " ms");

                        // return result
                        return result;

                    } else {
                        // serve cached result
                        final long timeStampNow = System.currentTimeMillis();
                        LOGGER.trace("[" + trueCacheName + "] HIT "
                                + thisJoinPointName
                                + " ["
                                + thisJoinPointArgs
                                + "] "
                                + (timeStampNow - timeStamp) + " ms");

                        return cacheContent;
                    }
                }
            }

        } catch (final Exception ex) {
            LOGGER.error("Caching aspect", ex);
            return thisJoinPoint.proceed();
        }
    }

    /**
     * Construct the key for the given invocation.
     *
     * @param thisJoinPoint the invocation parameters.
     * @return the unique cache key.
     */
    private String constructCacheKey(final ProceedingJoinPoint thisJoinPoint) {
        return new StringBuilder()
                .append(thisJoinPoint.getSignature().getDeclaringType().getCanonicalName())
                .append(".")
                .append(thisJoinPoint.getSignature().getName())
                .toString();
    }

    /**
     * Returns the arguments of the current join point as a string.
     *
     * @param joinPoint the joint point object.
     * @return string representing the arguments of this join point
     */
    public final String getJointPointArgs(final JoinPoint joinPoint) {
        final StringBuilder buf = new StringBuilder();

        for (final Object arg : joinPoint.getArgs()) {
            buf.append(arg.hashCode());
            buf.append("_");
        }
        return buf.toString().replaceAll("\\+$", "");
    }

}
