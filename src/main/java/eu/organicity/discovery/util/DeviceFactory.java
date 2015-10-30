package eu.organicity.discovery.util;

import com.amaxilatis.orion.model.OrionContextElement;
import eu.organicity.discovery.model.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DeviceFactory {
    /**
     * a log4j logger to print messages.
     */
    private static final Logger LOGGER = Logger.getLogger(DeviceFactory.class);

    static DeviceOwner patras = new DeviceOwner(0, "urn:oc:entity:patras", "Patras", "http://www.multistick.gr/files/images/dimos_patron.png", "http://cti.gr", "", new Location("Patras", "Greece", "GR"));
    static DeviceOwner london = new DeviceOwner(0, "urn:oc:entity:london", "London", "http://cliparts.co/cliparts/LTd/jL4/LTdjL4djc.jpg", "https://en.wikipedia.org/wiki/London", "", new Location("London", "United Kingdom", "UK"));
    static DeviceOwner santander= new DeviceOwner(0, "urn:oc:entity:santander", "Santander", "http://cliparts.co/cliparts/LTd/jL4/LTdjL4djc.jpg", "https://en.wikipedia.org/wiki/Santander", "", new Location("Santandeer", "Span", "ES"));

    public static Device covert(OrionContextElement contextElement) {
        final Device device = new Device();
        device.setId(Math.abs(contextElement.getId().hashCode()));
        device.setUuid(contextElement.getId());
        device.setName(contextElement.getId());
        final DeviceData data = new DeviceData();
        if (device.getName().startsWith("urn:oc:entity:santander")
                || device.getName().startsWith("urn:x-iot:smartsantander")) {
            //TODO: add santander owner
            device.setProvider(santander);
        } else if (device.getName().startsWith("urn:oc:entity:london")) {
            //TODO: add london owner
            device.setProvider(london);
        } else if (device.getName().startsWith("urn:oc:entity:aarhus")) {
            //TODO: add aarhus owner
        } else if (device.getName().startsWith("urn:oc:entity:patras:")) {
            //TODO: add patras owner
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
                    DeviceSensor sensor = new DeviceSensor();
                    sensor.setId(Math.abs(type.hashCode()));
                    sensor.setName(name);
                    sensor.setValue(Double.parseDouble(value));
                    sensor.setMetadata_id(type);
                    if (stringObjectMap.containsKey("metadatas")) {
                        ArrayList<Map<String, Object>> metadatas = (ArrayList<Map<String, Object>>) stringObjectMap.get("metadatas");
                        for (Map<String, Object> metadata : metadatas) {
                            LOGGER.info("metadata:" + metadata);
                            String metadataName = (String) metadata.get("name");
                            String metadataType = (String) metadata.get("type");
                            String metadataValue = (String) metadata.get("value");
                            if (metadataName.equals("unit")) {
                                sensor.setUnit(metadataValue);
                            }
                        }
                    }
                    data.getAtttributes().add(sensor);
                } catch (NumberFormatException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        }
        for (DeviceSensor deviceSensor : device.getData().getAtttributes()) {
            deviceSensor.setUpdated_at(device.getLast_reading_at());
        }
        return device;
    }
}
