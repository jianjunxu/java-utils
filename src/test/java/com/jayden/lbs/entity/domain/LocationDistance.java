package com.jayden.lbs.entity.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jayden on 16/11/16.
 */
public class LocationDistance implements Serializable {
    private static final long serialVersionUID = 8046365982360050041L;
    private long id;
    private long originId;
    private long destId;
    /**
     * 距离 单位：m
     */
    private long distance;
    /**
     * 耗时 单位：s
     */
    private long duration;
    private Date gmtCreated;
    private Date gmtModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOriginId() {
        return originId;
    }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public long getDestId() {
        return destId;
    }

    public void setDestId(long destId) {
        this.destId = destId;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
