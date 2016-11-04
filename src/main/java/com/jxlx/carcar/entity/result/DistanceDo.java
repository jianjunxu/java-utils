package com.jxlx.carcar.entity.result;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/4.
 */
public class DistanceDo implements Serializable {
    private static final long serialVersionUID = 2786740444160441724L;

    /**
     * 起点坐标，起点坐标序列号（从１开始）
     */
    private String origin_id;

    /**
     * 终点坐标，终点坐标序列号（从１开始）
     */
    private String dest_id;

    /**
     * 路径距离，单位：米
     */
    private String distance;

    /**
     * 预计行驶时间，单位：秒
     */
    private String duration;

    /**
     * 错误信息，仅在后台引擎返回错误码时返回这个字段，字段内容详见路径距离计算错误信息
     */
    private String info;

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getDest_id() {
        return dest_id;
    }

    public void setDest_id(String dest_id) {
        this.dest_id = dest_id;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
