package com.jxlx.carcar.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.convert.CarConverter;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.result.DistanceResult;
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
public class MapServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapServer.class);

    /**
     * 距离测量
     *
     * @param param
     * @return
     */
    public DistanceResult getDistance(DistanceParam param) {
        Preconditions.checkNotNull(param, "DistanceParam is null.");
        String methodURI = Constant.DISTANCE_URI;
        Map<String, String> parameters = CarConverter.convertDistanceParam(param);
        String url = NetWorkURL.toURL(methodURI, parameters);
        HttpGet httpGet = new HttpGet(url);
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
        return JSON.parseObject(response, DistanceResult.class);
    }

    public static void main(String[] args) {
        MapServer server = new MapServer();
        DistanceParam param = new DistanceParam();
        param.setKey(Constant.KEY_CAR_LINE);
        param.setOrigins("116.506218,40.006226");
        param.setDestination("116.480665,39.996404");
        DistanceResult result = server.getDistance(param);
        LOGGER.info("------result:" + JSON.toJSONString(result));
    }
}
