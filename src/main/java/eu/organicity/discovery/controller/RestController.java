package eu.organicity.discovery.controller;

import eu.organicity.discovery.model.Device;
import eu.organicity.discovery.service.DeviceFactory;
import eu.organicity.discovery.service.OrionService;
import eu.organicity.discovery.util.EntityId;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Dimitrios Amaxilatis.
 */
@Controller
public class RestController {
    /**
     * a log4j logger to print messages.
     */
    private static final Logger LOGGER = Logger.getLogger(RestController.class);


    @Autowired
    OrionService orionService;
    @Autowired
    DeviceFactory deviceFactory;

    private List<Device> storedEntitiesResponse;

    @ResponseBody
    @RequestMapping(value = "/v1/entities", method = RequestMethod.GET, produces = "application/json")
    public List<Device> getDevices() {
        try {
            final List<Device> entitiesResponse = orionService.getEntities();
            storedEntitiesResponse = entitiesResponse;
            return entitiesResponse;
        } catch (Exception e) {
            return storedEntitiesResponse;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/v1/id", method = RequestMethod.GET, produces = "application/json")
    public EntityId getIntegerId(@RequestParam(value = "uuid") final String uuid) {
        LOGGER.info("uuid:" + uuid);
        return deviceFactory.getId(uuid);
    }

    @Autowired
    private org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate;

    @ResponseBody
    @RequestMapping(value = "/v1/uuid", method = RequestMethod.GET, produces = "application/json")
    public EntityId getUuid(@RequestParam(value = "id") final int id) {
        return (EntityId) redisTemplate.opsForHash().get("oc-uuid-val", id);
    }

}
