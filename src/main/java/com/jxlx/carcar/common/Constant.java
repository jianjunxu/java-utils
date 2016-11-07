package com.jxlx.carcar.common;

/**
 * Created by jayden on 16/11/4.
 */
public interface Constant {
    /** 用户唯一标识 用户在高德地图官网申请 */
    String KEY_CAR_LINE = "d90b58069d86d84624d73a098e7b4383";
    /** host */
    String MAP_API_HOST = "http://restapi.amap.com";
    /** 距离测量uri */
    String DISTANCE_URI = "/v3/distance";
    /** 关键字搜索位置信息 */
    String SEARCH_PLACE_URI = "/v3/place/text";
    /** 批量请求接口 */
    String BATCH_REQUEST_URI = "/v3/batch";
}
