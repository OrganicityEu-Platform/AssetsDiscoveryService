package eu.organicity.discovery.controller;

import eu.organicity.discovery.model.Device;
import eu.organicity.discovery.service.OrionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @ResponseBody
    @RequestMapping(value = "/v1/entities", method = RequestMethod.GET, produces = "application/json")
    public List<Device> getDevices() {
        return orionService.getDevices();
    }
}
