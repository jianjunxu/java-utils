package com.jxlx.carcar.entity.result;

import com.jxlx.carcar.entity.dos.RoutDo;

import java.util.List;

/**
 * Created by jayden on 16/11/8.
 */
public class DirectionResult extends BaseResult {
    private static final long serialVersionUID = -4326052579852164000L;
    private List<RoutDo> route;

    public List<RoutDo> getRoute() {
        return route;
    }

    public void setRoute(List<RoutDo> route) {
        this.route = route;
    }
}
