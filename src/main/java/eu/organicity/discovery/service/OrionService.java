package eu.organicity.discovery.service;

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.ContextElementList;
import com.amaxilatis.orion.model.OrionContextElementWrapper;
import eu.organicity.discovery.cache.Cachable;
import eu.organicity.discovery.model.Device;
import eu.organicity.discovery.util.DeviceFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


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

    private SimpleDateFormat df;

    private OrionClient orionClient;

    @PostConstruct
    public void init() {
//        orionClient = new OrionClient("http://195.220.224.231:1026", "");
        orionClient = new OrionClient("http://mu.tlmat.unican.es:8099", "");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
    }

    @Cachable(cacheName = "entities")
    public List<Device> getDevices() {
        List<Device> resources = new ArrayList<Device>();

        try {
            ContextElementList entities = orionClient.listContextEntities();
            for (final OrionContextElementWrapper orionContextElementWrapper : entities.getContextResponses()) {
                try {
                    resources.add(DeviceFactory.covert(orionContextElementWrapper.getContextElement()));
                } catch (Exception e) {
                    LOGGER.error(e, e);
                }
            }

            return resources;
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    ;
}
