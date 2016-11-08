package com.jxlx.carcar.entity;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/7.
 */
public class DistanceResDo implements Serializable {
    private static final long serialVersionUID = -8438659167244174578L;
    private String oriId;
    private String destId;
    private String distance;
    private String duration;
    private String oriLocation;
    private String destLocation;

    public String getOriId() {
        return oriId;
    }

    public void setOriId(String oriId) {
        this.oriId = oriId;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOriLocation() {
        return oriLocation;
    }

    public void setOriLocation(String oriLocation) {
        this.oriLocation = oriLocation;
    }

    public String getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(String destLocation) {
        this.destLocation = destLocation;
    }
}
