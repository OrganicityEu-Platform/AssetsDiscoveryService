package eu.organicity.discovery.model;

import java.io.Serializable;

/**
 * Model for a SmartCitizen DeviceSensor Object.
 *
 * @author Dimitrios Amaxilatis
 */
public class DeviceSensor implements Serializable {
    private int id;
    private String name;
    private String unit;
    private String updated_at;
    private String attributes_id;
    private double value;
    private double prev_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAttributes_id() {
        return attributes_id;
    }

    public void setAttributes_id(String attributes_id) {
        this.attributes_id = attributes_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPrev_value() {
        return prev_value;
    }

    public void setPrev_value(double prev_value) {
        this.prev_value = prev_value;
    }

    @Override
    public String toString() {
        return "DeviceSensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", attributes_id='" + attributes_id + '\'' +
                ", value=" + value +
                ", prev_value=" + prev_value +
                '}';
    }
}
