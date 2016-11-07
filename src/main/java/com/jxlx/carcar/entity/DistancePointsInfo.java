package com.jxlx.carcar.entity;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/7.
 */
public class DistancePointsInfo implements Serializable {
    private static final long serialVersionUID = 346394476962899392L;

    private String oriId;
    private String oriDisCity;
    private String oriDisPoints;
    private String oriLocation;
    private String destId;
    private String destDisCity;
    private String destDisPoints;
    private String destLocation;
    private String distance;
    private String duration;

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

    public String getOriDisCity() {
        return oriDisCity;
    }

    public void setOriDisCity(String oriDisCity) {
        this.oriDisCity = oriDisCity;
    }

    public String getOriDisPoints() {
        return oriDisPoints;
    }

    public void setOriDisPoints(String oriDisPoints) {
        this.oriDisPoints = oriDisPoints;
    }

    public String getDestDisCity() {
        return destDisCity;
    }

    public void setDestDisCity(String destDisCity) {
        this.destDisCity = destDisCity;
    }

    public String getDestDisPoints() {
        return destDisPoints;
    }

    public void setDestDisPoints(String destDisPoints) {
        this.destDisPoints = destDisPoints;
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

    @Override
    public String toString() {
        return "DistancePointsInfo{" +
                "oriDisCity='" + oriDisCity + '\'' +
                ", oriDisPoints='" + oriDisPoints + '\'' +
                ", destDisCity='" + destDisCity + '\'' +
                ", destDisPoints='" + destDisPoints + '\'' +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
