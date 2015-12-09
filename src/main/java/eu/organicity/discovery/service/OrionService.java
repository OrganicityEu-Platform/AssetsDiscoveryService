package eu.organicity.discovery.service;

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.ContextElementList;
import com.amaxilatis.orion.model.OrionContextElementWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.organicity.discovery.cache.Cachable;
import eu.organicity.discovery.model.Device;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: theodori
 * Date: 9/4/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OrionService {

    /**
     * a log4j logger to print messages.
     */
    private static final Logger LOGGER = Logger.getLogger(OrionService.class);


    private static OrionClient centralOrion = new OrionClient("http://54.68.181.32:1026", "", "organicity", "/");
    private static OrionClient orionClientLondon = new OrionClient("http://146.169.46.162:1026/", "", "organicity", "/");
    private static OrionClient orionSantander = new OrionClient("http://mu.tlmat.unican.es:8099", "", "organicity", "/");
    private static OrionClient orionSmartphones = new OrionClient("http://195.220.224.231:1026", "", "organicity", "/");
    private static String ckanEntitiesPatras = "http://150.140.5.11:9998/v0/devices";

    @Autowired
    DeviceService deviceService;

    @Cachable(cacheName = "entities", expiration = 1)
    public List<Device> getEntities() {
        final List<Device> resources = new ArrayList<>();

        try {
            findAll(resources, centralOrion);
        } catch (Exception e1) {
            LOGGER.warn("Could not connect to Central");
            try {
                findAll(resources, orionClientLondon);
            } catch (Exception ignore) {
                LOGGER.warn("Could not connect to London");
            }
//        try {
//            findAll(resources, orionSantander);
//        } catch (Exception ignore) {
//            LOGGER.warn("Could not connect to Santander");
//        }
            try {
                findAll(resources, orionSmartphones);
            } catch (Exception ignore) {
                LOGGER.warn("Could not connect to Smartphones");
            }
        }
        try {
            findAll(resources, ckanEntitiesPatras);
        } catch (Exception e) {
            LOGGER.warn("Could not connect to ckan", e);
        }
        return resources;
    }


    private void findAll(final List<Device> resources, final OrionClient client) throws Exception {
        final long start = System.currentTimeMillis();
        if (client == null) {
            return;
        }
        ContextElementList entities;
        long offset = 0;
        do {
            entities = client.listContextEntities(offset);
            offset += entities.getContextResponses().size();
            for (final OrionContextElementWrapper orionContextElementWrapper : entities.getContextResponses()) {
                try {
                    Device device = deviceService.convert(orionContextElementWrapper.getContextElement());
                    if (device.getProvider() != null) {
                        if (device.getData().getLocation().getLatitude() == 0 || device.getData().getLocation().getLongitude() == 0) {
                        } else {
                            resources.add(device);
                        }
                    } else {
                        LOGGER.warn(orionContextElementWrapper.getContextElement().getId() + " has no provider!");
                    }
                } catch (Exception e) {
                    LOGGER.error(e, e);
                }
            }
        } while (entities.hasMore(offset));
        LOGGER.info("[" + client + "]Took: " + (System.currentTimeMillis() - start));
    }

    private void findAll(final List<Device> resources, final String url) throws Exception {
        final long start = System.currentTimeMillis();

        final List<Device> entities = new ObjectMapper().readValue(new URL(url), new TypeReference<List<Device>>() {
        });
        for (final Device entity : entities) {
            if (entity.getData().getLocation().getLatitude() == 0 || entity.getData().getLocation().getLongitude() == 0) {
            } else {
                resources.add(entity);
            }
        }
        LOGGER.info("[" + url + "]Took: " + (System.currentTimeMillis() - start));
    }
}
