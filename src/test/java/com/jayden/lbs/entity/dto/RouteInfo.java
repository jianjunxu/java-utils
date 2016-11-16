package com.jayden.lbs.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jayden on 16/11/16.
 */
public class RouteInfo implements Serializable {
    private static final long serialVersionUID = 6964639640053606094L;
    private List<CostInfo> paths;

    public List<CostInfo> getPaths() {
        return paths;
    }

    public void setPaths(List<CostInfo> paths) {
        this.paths = paths;
    }
}
