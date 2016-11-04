package com.jxlx.carcar.entity.result;

import java.io.Serializable;

/**
 * Created by jayden on 16/11/4.
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 4204776512624151843L;

    /** 返回结果状态值，值为0或1,0表示请求失败；1表示请求成功 */
    private String status;
    /** 返回状态说明，status为0时，info返回错误原；否则返回“OK”。详情参阅:http://lbs.amap.com/api/webservice/guide/tools/info/ */
    private String info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
