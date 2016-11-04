package com.jxlx.carcar.entity;

/**
 * Created by jayden on 16/11/4.
 */
public class Coordinates {
    private String desName;
    private String latitude;
    private String longitude;

    public Coordinates() {
    }

    public Coordinates(String desName, String latitude, String longitude) {
        this.desName = desName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDesName() {
        return desName;
    }

    public void setDesName(String desName) {
        this.desName = desName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
