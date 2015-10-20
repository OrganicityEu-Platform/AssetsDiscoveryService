package eu.organicity.discovery.model;

/**
 * Model for a SmartCitizen DeviceSensor Object.
 *
 * @author Dimitrios Amaxilatis
 */
public class DeviceSensor {
    private int id;
    private String ancestry;
    private String name;
    private String description;
    private String unit;
    private String created_at;
    private String updated_at;
    private int measurement_id;
    private String uuid;
    private String value;
    private String raw_value;
    private String prev_value;
    private String prev_raw_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(int measurement_id) {
        this.measurement_id = measurement_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRaw_value() {
        return raw_value;
    }

    public void setRaw_value(String raw_value) {
        this.raw_value = raw_value;
    }

    public String getPrev_value() {
        return prev_value;
    }

    public void setPrev_value(String prev_value) {
        this.prev_value = prev_value;
    }

    public String getPrev_raw_value() {
        return prev_raw_value;
    }

    public void setPrev_raw_value(String prev_raw_value) {
        this.prev_raw_value = prev_raw_value;
    }
}
