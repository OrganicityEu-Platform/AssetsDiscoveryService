package eu.organicity.discovery.util;

import java.io.Serializable;

public class EntityId implements Serializable {
    int id;
    String uuid;

    public EntityId() {
    }

    public EntityId(int id, String uuid) {
        this.id = id;
        this.uuid = uuid;
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
}