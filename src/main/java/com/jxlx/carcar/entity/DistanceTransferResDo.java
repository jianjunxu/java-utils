package com.jxlx.carcar.entity;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/7.
 */
public class DistanceTransferResDo implements Serializable {
    private static final long serialVersionUID = -8438659167244174578L;
    /** 出发地id */
    private String oriId;
    /** 第二个中转地id */
    private String transferId;
    /** 目的地id */
    private String destId;
    /** 距离 */
    private String distance;
    /** 耗时 */
    private String duration;

    public String getOriId() {
        return oriId;
    }

    public void setOriId(String oriId) {
        this.oriId = oriId;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
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
}
