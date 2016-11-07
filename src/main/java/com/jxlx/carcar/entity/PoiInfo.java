package com.jxlx.carcar.entity;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/4.
 */
public class PoiInfo implements Serializable {
    private static final long serialVersionUID = -8848295610597162308L;

    /** 唯一ID */
    private String id;
    /** 名称 */
    private String name;
    /** 地址 */
    private String address;
    /** 经纬度 */
    private String location;
    /** 城市名 */
    private String cityname;
    /** 区域名称 */
    private String adname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    @Override
    public String toString() {
        return "PoiInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", cityname='" + cityname + '\'' +
                ", adname='" + adname + '\'' +
                '}';
    }
}
