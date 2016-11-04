package com.jxlx.carcar.entity.params;

/**
 * Created by jayden on 16/11/4.
 */
public class DistanceParam extends BaseParam {

    private static final long serialVersionUID = -3970328484731093654L;
    /** 出发点,支持100个坐标对，坐标对见用“| ”分隔；经度和纬度用","分隔 eg:116.481028,39.989643|114.481028,39.989643|115.481028,39.989643 */
    private String origins;
    /** 目的地，规则： lon,lat（经度,纬度）， “,”分割 如117.500244, 40.417801 经纬度小数点不超过6位 */
    private String destination;
    /**
     * 0：直线距离
     * 1：驾车导航距离（仅支持国内坐标）。
     * 必须指出，当为1时会考虑路况，故在不同时间请求返回结果可能不同
     * 2：公交规划距离（仅支持同城坐标）
     * 3：步行规划距离（仅支持5km之间的距离）
     *
     * 缺省值 1
     */
    private String type;
    private String sig;
    /** 返回数据格式类型, 可选值：JSON,XML 缺省值JSON*/
    private String output;
    /** callback值是用户定义的函数名称，此参数只在output=JSON时有效 可选 */
    private String callback;

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
