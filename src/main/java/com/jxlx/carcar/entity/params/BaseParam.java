package com.jxlx.carcar.entity.params;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/4.
 */
public class BaseParam implements Serializable {
    private static final long serialVersionUID = -3915273430959201047L;
    /** 用户在高德地图官网申请Web服务API类型KEY */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
