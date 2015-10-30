package eu.organicity.discovery.model;

import java.io.Serializable;

/**
 * Created by amaxilatis on 20/10/2015.
 */
public class DataLocation implements Serializable{
    private double latitude;
    private double longitude;
    private String city;
    private String country_code;
    private String country;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "DataLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", country_code='" + country_code + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
