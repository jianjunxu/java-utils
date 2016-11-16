package com.jayden.lbs.entity.params;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/16.
 * 驾车路径规划参数
 *
 */
public class RoutePlanParams implements Serializable {
    private static final long serialVersionUID = 4475088684360413917L;
    private String key;
    /**
     * 出发点
     * 经度,纬度
     */
    private String origin;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 驾车选择策略
     * 默认 0
     * 0-速度优先（时间）
     * 1-费用优先（不走收费路段的最快道路）
     * 2-距离优先
     * 3-不走快速路
     * 4-躲避拥堵
     * 5-多策略（同时使用速度优先、费用优先、距离优先、三个策略计算路径）
     * 6-不走高速
     * 7-不走高速且避免收费
     * 8-躲避收费和拥堵
     * 9-不走高速且躲避收费和拥堵
     */
    private String strategy = "2";
    private String waypoints = "";
    private String output = "JSON";

    /**
     * 额外参数
     */
    private String oriId;
    private String transferId;
    private String destId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

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
}
