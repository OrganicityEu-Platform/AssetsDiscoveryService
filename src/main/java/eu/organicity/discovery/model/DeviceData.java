package eu.organicity.discovery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DeviceData {
    private String recorded_at;
    private DataLocation location;
    private List<DeviceSensor> atttributes;

    public DeviceData() {
        this.atttributes = new ArrayList<DeviceSensor>();
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

    public List<DeviceSensor> getAtttributes() {
        return atttributes;
    }

    public void setAtttributes(List<DeviceSensor> atttributes) {
        this.atttributes = atttributes;
    }
}
