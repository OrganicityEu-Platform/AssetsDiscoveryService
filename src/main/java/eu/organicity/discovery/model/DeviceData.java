package eu.organicity.discovery.model;

import java.util.List;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DeviceData {
    private String recorded_at;
    private String added_at;
    private DataLocation location;
    private List<DeviceSensor> sensors;

    public String getRecorded_at() {
        return recorded_at;
    }

    public void setRecorded_at(String recorded_at) {
        this.recorded_at = recorded_at;
    }

    public String getAdded_at() {
        return added_at;
    }

    public void setAdded_at(String added_at) {
        this.added_at = added_at;
    }

    public DataLocation getLocation() {
        return location;
    }

    public void setLocation(DataLocation location) {
        this.location = location;
    }

    public List<DeviceSensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<DeviceSensor> sensors) {
        this.sensors = sensors;
    }
}
