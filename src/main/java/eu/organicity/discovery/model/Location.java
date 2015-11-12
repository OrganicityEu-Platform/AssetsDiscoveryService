package eu.organicity.discovery.model;

import java.io.Serializable;

/**
 * @author Dimitrios Amaxilatis.
 */
public class Location implements Serializable {
    private String city;
    private String country;
    private String country_code;

    public Location() {
    }

    public Location(String city, String country, String country_code) {
        this.city = city;
        this.country = country;
        this.country_code = country_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
