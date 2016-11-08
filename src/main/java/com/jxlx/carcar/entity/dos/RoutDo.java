package com.jxlx.carcar.entity.dos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jayden on 16/11/8.
 * 驾车路径规划信息
 */
public class RoutDo implements Serializable {
    private static final long serialVersionUID = 7854830828649248669L;

    private String origin;
    private String destination;
    private String taxi_cost;
    private List<PathDo> paths;

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

    public String getTaxi_cost() {
        return taxi_cost;
    }

    public void setTaxi_cost(String taxi_cost) {
        this.taxi_cost = taxi_cost;
    }

    public List<PathDo> getPaths() {
        return paths;
    }

    public void setPaths(List<PathDo> paths) {
        this.paths = paths;
    }
}
