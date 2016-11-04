package com.jxlx.carcar.entity.params;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/4.
 */
public class BaseParam implements Serializable {
    private static final long serialVersionUID = -3915273430959201047L;
    /** 用户在高德地图官网申请Web服务API类型KEY */
    private String key;

    /** 返回数据格式类型, 可选值：JSON,XML 缺省值JSON*/
    private String output;
    /** callback值是用户定义的函数名称，此参数只在output=JSON时有效 可选 */
    private String callback;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
