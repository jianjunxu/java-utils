package com.jxlx.carcar.entity;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/8.
 */
public class PositionDo implements Serializable {
    private static final long serialVersionUID = -3238464944505643327L;
    private String pid;
    private String pcity;
    private String pname;
    private String location;

    public String getPid() {
        return pid;
    }

    public PositionDo() {
    }

    public PositionDo(String pid, String pcity, String pname, String location) {
        this.pid = pid;
        this.pcity = pcity;
        this.pname = pname;
        this.location = location;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPcity() {
        return pcity;
    }

    public void setPcity(String pcity) {
        this.pcity = pcity;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
