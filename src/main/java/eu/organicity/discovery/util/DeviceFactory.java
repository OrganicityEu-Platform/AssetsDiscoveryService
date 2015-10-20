package eu.organicity.discovery.util;

import com.amaxilatis.orion.model.OrionContextElement;
import eu.organicity.discovery.model.Device;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DeviceFactory {

    public static Device covert(OrionContextElement contextElement) {
        final Device device = new Device();
        device.setId(contextElement.getId().hashCode());
        device.setName(contextElement.getId());
        device.setDescription(contextElement.getId());
        return device;
    }
}
