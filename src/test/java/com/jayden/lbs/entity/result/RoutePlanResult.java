package com.jayden.lbs.entity.result;

import com.jayden.lbs.entity.dto.RouteInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jayden on 16/11/16.
 */
public class RoutePlanResult implements Serializable {
    private static final long serialVersionUID = -1121504688600994163L;
    /**
     * 结果状态值，值为0或1
     * 0：请求失败；1：请求成功
     */
    private int status;
    /**
     * 返回状态说明
     * status为0时，info返回错误原因，否则返回“OK”.
     * http://lbs.amap.com/api/webservice/guide/tools/info/
     */
    private String info;

    private List<RouteInfo> route;
    /**
     * 额外参数
     */
    private String oriId;
    private String transferId;
    private String destId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<RouteInfo> getRoute() {
        return route;
    }

    public void setRoute(List<RouteInfo> route) {
        this.route = route;
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
