package eu.organicity.discovery.service;

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.ContextElementList;
import com.amaxilatis.orion.model.OrionContextElement;
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
public class DeviceService {

    /**
     * a log4j logger to print messages.
     */
    private static final Logger LOGGER = Logger.getLogger(DeviceService.class);

    private SimpleDateFormat df;

    private OrionClient orionClient;


    @Cachable(cacheName = "contextElements", expiration = 5)
    public Device convert(OrionContextElement contextElement) {
        return DeviceFactory.covert(contextElement);
    }
}
