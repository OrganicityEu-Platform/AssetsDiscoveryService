package eu.organicity.discovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DeviceData implements Serializable {
    private String recorded_at;
    private DataLocation location;
    private List<DeviceSensor> attributes;

    public DeviceData() {
        this.attributes = new ArrayList<DeviceSensor>();
    }

    public String getRecorded_at() {
        return recorded_at;
    }

    public void setRecorded_at(String recorded_at) {
        this.recorded_at = recorded_at;
    }

    public DataLocation getLocation() {
        return location;
    }

    public void setLocation(DataLocation location) {
        this.location = location;
    }

    public List<DeviceSensor> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DeviceSensor> attributes) {
        this.attributes = attributes;
    }
}
