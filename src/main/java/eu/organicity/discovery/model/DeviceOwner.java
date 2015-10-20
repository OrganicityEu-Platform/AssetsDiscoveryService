package eu.organicity.discovery.model;

import java.util.List;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DeviceOwner {
    private int id;
    private String uuid;
    private String username;
    private String avatar;
    private String url;
    private String joined_at;
    private Location location;
    private List<Integer> device_ids;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJoined_at() {
        return joined_at;
    }

    public void setJoined_at(String joined_at) {
        this.joined_at = joined_at;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Integer> getDevice_ids() {
        return device_ids;
    }

    public void setDevice_ids(List<Integer> device_ids) {
        this.device_ids = device_ids;
    }
}
