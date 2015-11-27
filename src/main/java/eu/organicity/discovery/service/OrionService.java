package eu.organicity.discovery.service;

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.ContextElementList;
import com.amaxilatis.orion.model.OrionContextElementWrapper;
import eu.organicity.discovery.cache.Cachable;
import eu.organicity.discovery.model.Device;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.ProcessingException;
import java.net.ConnectException;
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


    private OrionClient centralOrion = new OrionClient("http://54.68.181.32:1026", "", "organicity", "/");
    private OrionClient orionClientLondon = null;// new OrionClient("http://195.220.224.231:1026", "", "organicity", "/");
    private OrionClient orionSantander = new OrionClient("http://mu.tlmat.unican.es:8099", "", "organicity", "/");
    private OrionClient orionSmartphones = new OrionClient("http://195.220.224.231:1026", "", "organicity", "/");

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
            try {
                findAll(resources, orionSantander);
            } catch (Exception ignore) {
                LOGGER.warn("Could not connect to Santander");
            }
            try {
                findAll(resources, orionSmartphones);
            } catch (Exception ignore) {
                LOGGER.warn("Could not connect to Smartphones");
            }
        }
        return resources;
    }


    private void findAll(List<Device> resources, final OrionClient client) throws Exception {
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
                    resources.add(deviceService.convert(orionContextElementWrapper.getContextElement()));
                } catch (Exception e) {
                    LOGGER.error(e, e);
                }
            }
        } while (entities.hasMore(offset));
    }
}
