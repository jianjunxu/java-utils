package com.jayden.lbs.entity.dto;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/16.
 */
public class CostInfo implements Serializable {
    private static final long serialVersionUID = 9011527112097794761L;
    /** 行驶距离 单位：米 */
    private String distance;
    /** 预计行驶时间 单位：秒 */
    private String duration;

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
}
