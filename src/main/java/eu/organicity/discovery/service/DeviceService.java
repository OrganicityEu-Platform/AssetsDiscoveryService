package eu.organicity.discovery.service;

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.OrionContextElement;
import eu.organicity.discovery.cache.Cachable;
import eu.organicity.discovery.model.*;
import eu.organicity.discovery.util.EntityId;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;


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

    private static final DeviceOwner patras = new DeviceOwner(0, "urn:oc:entity:patras", "Patras", "http://www.multistick.gr/files/images/dimos_patron.png", "http://cti.gr", "", new Location("Patras", "Greece", "GR"));
    private static final DeviceOwner london = new DeviceOwner(0, "urn:oc:entity:london", "London", "http://cliparts.co/cliparts/LTd/jL4/LTdjL4djc.jpg", "https://en.wikipedia.org/wiki/London", "", new Location("London", "United Kingdom", "UK"));
    private static final DeviceOwner santander = new DeviceOwner(0, "urn:oc:entity:santander", "Santander", "http://cliparts.co/cliparts/LTd/jL4/LTdjL4djc.jpg", "https://en.wikipedia.org/wiki/Santander", "", new Location("Santander", "Spain", "ES"));

    private SimpleDateFormat df;

    private OrionClient orionClient;

    @Autowired
    DeviceFactory deviceFactory;

    @Cachable(cacheName = "contextElements", expiration = 5)
    public Device convert(OrionContextElement contextElement) {
        return covert(contextElement);
    }

    public Device covert(OrionContextElement contextElement) {
        final Device device = new Device();

        final EntityId entityId = deviceFactory.getId(contextElement.getId());
        device.setId(entityId.getId());
        device.setUuid(contextElement.getId());
        device.setName(contextElement.getId());
        final DeviceData data = new DeviceData();
        if (device.getUuid().startsWith("urn:oc:entity:santander")) {
            device.setProvider(santander);
        } else if (device.getUuid().startsWith("urn:oc:entity:london")) {
            device.setProvider(london);
        } else if (device.getUuid().startsWith("urn:oc:entity:aarhus")) {
            //TODO: add aarhus owner
        } else if (device.getUuid().startsWith("urn:oc:entity:patras:")) {
            device.setProvider(patras);
        }
        device.setData(data);
        final DataLocation location = new DataLocation();
        data.setLocation(location);

        for (Map<String, Object> stringObjectMap : contextElement.getAttributes()) {
            String name = (String) stringObjectMap.get("name");
            String type = (String) stringObjectMap.get("type");
            String value = (String) stringObjectMap.get("value");
            LOGGER.debug("name:" + name);
            LOGGER.debug("type:" + type);
            if (type.contains("coords")) {
                //This converts the location data
                final String latitude = value.split(",")[0];
                final String longitude = value.split(",")[1];
                location.setLongitude(Double.parseDouble(longitude));
                location.setLatitude(Double.parseDouble(latitude));
            } else if (type.contains("latitude")) {
                //ignore legacy location latitude
            } else if (type.contains("longitude")) {
                //ignore legacy location longitude
            } else if (type.contains("ISO")) {
                //This converts the date recorded data
                data.setRecorded_at(value);
                device.setLast_reading_at(value);
            } else {
                try {
                    final EntityId sensorId = deviceFactory.getId(type);
                    final DeviceSensor sensor = new DeviceSensor();
                    sensor.setId(sensorId.getId());
                    sensor.setName(name);
                    sensor.setValue(Double.parseDouble(value));
                    sensor.setAttributes_id(sensorId.getUuid());
                    if (stringObjectMap.containsKey("metadatas")) {
                        ArrayList<Map<String, Object>> metadatas = (ArrayList<Map<String, Object>>) stringObjectMap.get("metadatas");
                        for (Map<String, Object> metadata : metadatas) {
                            LOGGER.info("metadata:" + metadata);
                            String metadataName = (String) metadata.get("name");
                            String metadataValue = (String) metadata.get("value");
                            if (metadataName.equals("unit")) {
                                sensor.setUnit(metadataValue);
                            }
                        }
                    }
                    data.getAttributes().add(sensor);
                } catch (NumberFormatException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        }
        for (DeviceSensor deviceSensor : device.getData().getAttributes()) {
            deviceSensor.setUpdated_at(device.getLast_reading_at());
        }
        return device;
    }
}
