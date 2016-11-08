package com.jxlx.carcar.entity.dos;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/8.
 */
public class PathDo implements Serializable {
    private static final long serialVersionUID = 3432699995120749399L;

    /** 行驶距离 单位：米 */
    private String distance;
    /** 预计行驶时间 单位：秒 */
    private String duration;
    /** 策略 */
    private String strategy;

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

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
