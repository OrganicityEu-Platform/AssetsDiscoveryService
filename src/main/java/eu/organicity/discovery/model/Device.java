package eu.organicity.discovery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class Device {
    private int id;
    private String uuid;
    private String name;
    private String description;
    private String state;
    private List<String> system_tags;
    private List<String> user_tags;
    private String last_reading_at;
    private String added_at;
    private String updated_at;
    private String mac_address;
    private DeviceOwner owner;
    private DeviceData data;
    private Kit kit;

    public Device() {
        system_tags = new ArrayList<String>();
        user_tags = new ArrayList<String>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getSystem_tags() {
        return system_tags;
    }

    public void setSystem_tags(List<String> system_tags) {
        this.system_tags = system_tags;
    }

    public List<String> getUser_tags() {
        return user_tags;
    }

    public void setUser_tags(List<String> user_tags) {
        this.user_tags = user_tags;
    }

    public String getLast_reading_at() {
        return last_reading_at;
    }

    public void setLast_reading_at(String last_reading_at) {
        this.last_reading_at = last_reading_at;
    }

    public String getAdded_at() {
        return added_at;
    }

    public void setAdded_at(String added_at) {
        this.added_at = added_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public DeviceOwner getOwner() {
        return owner;
    }

    public void setOwner(DeviceOwner owner) {
        this.owner = owner;
    }

    public DeviceData getData() {
        return data;
    }

    public void setData(DeviceData data) {
        this.data = data;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", system_tags=" + system_tags +
                ", user_tags=" + user_tags +
                ", last_reading_at='" + last_reading_at + '\'' +
                ", added_at='" + added_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", mac_address='" + mac_address + '\'' +
                ", owner=" + owner +
                ", data=" + data +
                ", kit=" + kit +
                '}';
    }
}

