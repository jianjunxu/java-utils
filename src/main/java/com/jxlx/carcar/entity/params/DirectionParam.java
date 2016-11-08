package com.jxlx.carcar.entity.params;

/**
 * Created by jayden on 16/11/8.
 */
public class DirectionParam extends BaseParam {
    private static final long serialVersionUID = -3157340523348853375L;
    /** 出发点 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位 */
    private String origin;
    /** 目的地 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位 */
    private String destination;
    /**
     * 驾车选择策略 默认2
     *
     * 0速度优先（时间）
     * 1费用优先（不走收费路段的最快道路）
     * 2距离优先
     * 3不走快速路
     * 4躲避拥堵
     * 5多策略（同时使用速度优先、费用优先、距离优先三个策略计算路径）。
     其中必须说明，就算使用三个策略算路，会根据路况不固定的返回一~三条路径规划信息。
     * 6不走高速
     * 7不走高速且避免收费
     * 8躲避收费和拥堵
     * 9不走高速且躲避收费和拥堵
     */
    private String strategy = "2";
    /**
     * 途经点
     * 经度和纬度用","分割，经度在前，纬度在后，小数点后不超过6位，坐标点之间用";"分隔
     * 最大数目：16个坐标点。如果输入多个途径点，则按照用户输入的顺序进行路径规划
     */
    private String waypoints;

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
}
