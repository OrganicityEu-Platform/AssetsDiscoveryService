package eu.organicity.discovery.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.organicity.discovery.model.Device;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class ModelTest {
    protected static final Logger LOGGER = Logger.getLogger(ModelTest.class);

    ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        mapper = new ObjectMapper();
    }

    @Test
    public void testParseSmartCitizenDevices() throws IOException {
        List<Device> resources = mapper.readValue(new URL("http://localhost/devices.html"), new TypeReference<List<Device>>() {
        });
        for (Device resource : resources) {
            LOGGER.info(resource);
        }
    }
}
