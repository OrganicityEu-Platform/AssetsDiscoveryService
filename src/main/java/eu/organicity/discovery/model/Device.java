package eu.organicity.discovery.model;

import java.io.Serializable;

/**
 * @author Dimitrios Amaxilatis.
 */
public class Device implements Serializable {
    private int id;
    private String uuid;
    private String name;
    private String last_reading_at;
    private DeviceOwner provider;
    private DeviceData data;
    private Kit entities_type;

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

    public String getLast_reading_at() {
        return last_reading_at;
    }

    public void setLast_reading_at(String last_reading_at) {
        this.last_reading_at = last_reading_at;
    }

    public DeviceOwner getProvider() {
        return provider;
    }

    public void setProvider(DeviceOwner provider) {
        this.provider = provider;
    }

    public DeviceData getData() {
        return data;
    }

    public void setData(DeviceData data) {
        this.data = data;
    }

    public Kit getEntities_type() {
        return entities_type;
    }

    public void setEntities_type(Kit entities_type) {
        this.entities_type = entities_type;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", last_reading_at='" + last_reading_at + '\'' +
                ", provider=" + provider +
                ", data=" + data +
                ", entities_type=" + entities_type +
                '}';
    }
}

