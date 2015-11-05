package eu.organicity.discovery.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The generic cache interface.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cachable {

    /**
     * The name of the cache to use.
     * <p>
     * return the name of the cache.
     */
    String cacheName();

    /**
     * The name of the cache to use.
     * <p>
     * return the name of the cache.
     */
    int expiration();

    /**
     * If this is an evict operation.
     * <p>
     * return true if this is an evict operation.
     */
    boolean evict() default false;

}
