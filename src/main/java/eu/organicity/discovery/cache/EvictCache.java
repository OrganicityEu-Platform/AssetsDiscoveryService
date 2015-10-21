package eu.organicity.discovery.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for cache eviction.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EvictCache {

    /**
     * The name of the cache to evict.
     *
     * return the name of the cache.
     */
    String cacheName() default "";

    /**
     * Multiple names of caches to evict.
     *
     * return the name of the cache.
     */
    String[] cachesToEvict() default {};

    /**
     * The index of the argument containing the user ID.
     *
     * return the index of the argument.
     */
    int userId() default 0;

    /**
     * The index of the argument containing the resource ID.
     *
     * return the index of the argument.
     */
    int resourceId() default 0;

}
