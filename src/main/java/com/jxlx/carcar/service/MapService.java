package com.jxlx.carcar.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.convert.CarConverter;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DistanceResult;
import com.jxlx.carcar.entity.result.PlaceResult;
import com.jxlx.carcar.utils.AbstractResponseHandler;
import com.jxlx.carcar.utils.HttpClientUtils;
import com.jxlx.carcar.utils.NetWorkURL;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by jayden on 16/11/4.
 */
public class MapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);

    /**
     * 距离测量
     *
     * @param param
     * @return
     * eg.http://restapi.amap.com/v3/distance?origins=116.506218%2C40.006226&key=d90b58069d86d84624d73a098e7b4383&destination=116.480665%2C39.996404
     */
    public DistanceResult getDistance(DistanceParam param) {
        Preconditions.checkNotNull(param, "DistanceParam is null.");
        String methodURI = Constant.DISTANCE_URI;
        Map<String, String> parameters = CarConverter.convertDistanceParam(param);
        String url = NetWorkURL.toURL(methodURI, parameters);
        String response = httpGetAccess(new HttpGet(url));
        return JSON.parseObject(response, DistanceResult.class);
    }

    /**
     * 根据关键字获取位置信息，经纬度等信息
     * @param placeParam
     * @return
     * eg：http://restapi.amap.com/v3/place/text?keywords=%E5%A4%A9%E5%AE%89%E9%97%A8&page=1&citylimit=true&offset=1&key=d90b58069d86d84624d73a098e7b4383&city=beijing
     */
    public PlaceResult getPlaceInfo(PlaceParam placeParam) {
        Preconditions.checkNotNull(placeParam, "PlaceParam is null.");
        String methodURI = Constant.SEARCH_PLACE_URI;
        Map<String, String> parameters = CarConverter.convertPlaceParam(placeParam);
        String url = NetWorkURL.toURL(methodURI, parameters);
        String response = httpGetAccess(new HttpGet(url));
        return JSON.parseObject(response, PlaceResult.class);
    }

    /**
     * http get
     * @param httpGet
     * @return response
     */
    private String httpGetAccess(HttpGet httpGet){
        String response = "";
        try {
            HttpClientUtils httpClient = new HttpClientUtils();
            response = httpClient.executeWithLog(httpGet, new AbstractResponseHandler<String>() {
                @Override
                public String handle(HttpEntity entity) throws IOException {
                    return EntityUtils.toString(entity);
                }
            });
        } catch (Exception e) {
            LOGGER.error("IOException.ex:{}", e.getMessage(), e);
        }
        return response;
    }
    public static void main(String[] args) {
        MapService server = new MapService();
        DistanceParam param = new DistanceParam();
        param.setKey(Constant.KEY_CAR_LINE);
        param.setOrigins("116.506218,40.006226");
        param.setDestination("116.480665,39.996404");
        DistanceResult result = server.getDistance(param);
        LOGGER.info("------result:" + JSON.toJSONString(result));

//        PlaceParam placeParam = new PlaceParam();
//        placeParam.setKey(Constant.KEY_CAR_LINE);
//        placeParam.setCity("beijing");
//        placeParam.setKeywords("天安门");
//        PlaceResult placeResult = server.getPlaceInfo(placeParam);
//        LOGGER.info("------placeResult:" + JSON.toJSONString(placeResult));
    }
}