package eu.organicity.discovery.service;

import eu.organicity.discovery.cache.Cachable;
import eu.organicity.discovery.util.EntityId;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceFactory {
    /**
     * a log4j logger to print messages.
     */
    private static final Logger LOGGER = Logger.getLogger(DeviceFactory.class);

    @Autowired
    private org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate;

    @Cachable(cacheName = "ids", expiration = 50)
    public EntityId getId(final String uuid) {
        int deviceId = Math.abs(uuid.hashCode());
        final EntityId entityId = new EntityId(deviceId, uuid);
        redisTemplate.opsForHash().put("oc-uuid-val", deviceId, entityId);
        return entityId;
    }
}
