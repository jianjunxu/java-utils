package com.jxlx.carcar.entity.result;

import com.jxlx.carcar.entity.dos.RoutDo;

import java.util.List;

/**
 * Created by jayden on 16/11/8.
 */
public class DirectionResult extends BaseResult {
    private static final long serialVersionUID = -4326052579852164000L;
    private String originId;
    private String destId;
    private String waypointsId;

    private List<RoutDo> route;

    public List<RoutDo> getRoute() {
        return route;
    }

    public void setRoute(List<RoutDo> route) {
        this.route = route;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getWaypointsId() {
        return waypointsId;
    }

    public void setWaypointsId(String waypointsId) {
        this.waypointsId = waypointsId;
    }
}
