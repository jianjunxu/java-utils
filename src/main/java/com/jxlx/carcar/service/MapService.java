package com.jxlx.carcar.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.convert.CarConverter;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DistanceResult;
import com.jxlx.carcar.entity.result.PlaceResult;
import com.jxlx.carcar.utils.AbstractResponseHandler;
import com.jxlx.carcar.utils.HttpClientUtils;
import com.jxlx.carcar.utils.NetWorkURL;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
     * @return eg.http://restapi.amap.com/v3/distance?origins=116.506218%2C40.006226&key=d90b58069d86d84624d73a098e7b4383&destination=116.480665%2C39.996404
     */
    public DistanceResult getDistance(DistanceParam param) {
        Preconditions.checkNotNull(param, "DistanceParam is null.");
        String methodURI = Constant.MAP_API_HOST + Constant.DISTANCE_URI;
        Map<String, String> parameters = CarConverter.convertDistanceParam(param);
        String url = NetWorkURL.toURL(methodURI, parameters);
        String response = httpGetAccess(new HttpGet(url));
        if (StringUtils.isBlank(response)) {
            LOGGER.info("response is blank.param:{}", JSON.toJSONString(param));
            return null;
        }
        DistanceResult result = JSON.parseObject(response, DistanceResult.class);
        if (result == null) {
            LOGGER.info("JSON.parseObject error.response:{}", JSON.toJSONString(response));
            return null;
        }
        result.setOriId(param.getOriId());
        result.setDestId(param.getDestId());
        return result;
    }

    public List<DistanceResult> batchGetDistance(List<DistanceParam> params) {
        List<DistanceResult> resultList = Lists.newArrayList();
        for (DistanceParam param : params) {
            DistanceResult result = getDistance(param);
            if (result != null) {
                resultList.add(result);
            } else {
                LOGGER.warn("result is null.placeParams:{}", JSON.toJSONString(param));
            }
        }
        return resultList;
    }

    /**
     * 根据关键字获取位置信息，经纬度等信息
     *
     * @param placeParam
     * @return eg：http://restapi.amap.com/v3/place/text?keywords=%E5%A4%A9%E5%AE%89%E9%97%A8&page=1&citylimit=true&offset=1&key=d90b58069d86d84624d73a098e7b4383&city=beijing
     */
    public PlaceResult getPlaceInfo(PlaceParam placeParam) {
        Preconditions.checkNotNull(placeParam, "PlaceParam is null.");
        String methodURI = Constant.MAP_API_HOST + Constant.SEARCH_PLACE_URI;
        Map<String, String> parameters = CarConverter.convertPlaceParam(placeParam);
        String url = NetWorkURL.toURL(methodURI, parameters);
        String response = httpGetAccess(new HttpGet(url));
        if (StringUtils.isBlank(response)) {
            LOGGER.info("response is blank.placeParam:{}", JSON.toJSONString(placeParam));
            return null;
        }
        PlaceResult result = JSON.parseObject(response, PlaceResult.class);
        if (result == null) {
            LOGGER.info("JSON.parseObject error.response:{}", JSON.toJSONString(response));
            return null;
        }
        result.setPlaceId(placeParam.getPlaceId());
        return result;
    }

    /**
     * 批量 TODO 后改进
     *
     * @param placeParams
     * @return
     */
    public List<PlaceResult> batchGetPlaceInfo(List<PlaceParam> placeParams) {
        List<PlaceResult> resultList = new ArrayList(placeParams.size());
        for (PlaceParam param : placeParams) {
            PlaceResult result = getPlaceInfo(param);
            if (result != null) {
                resultList.add(result);
            } else {
                LOGGER.warn("result is null.placeParams:{}", JSON.toJSONString(param));
            }
        }
        return resultList;
    }

    /**
     * http://lbs.amap.com/api/webservice/guide/api/batchrequest/
     */
    public List<PlaceResult> batchRequestPlaceInfo(List<PlaceParam> placeParams){
        JSONObject params = new JSONObject();
        JSONArray arr = new JSONArray();
        params.put("ops",arr);
        for (PlaceParam placeParam: placeParams){
            Map<String, String> map = CarConverter.convertPlaceParam(placeParam);
            String url = NetWorkURL.toURL(Constant.SEARCH_PLACE_URI, map);
            JSONObject entity = new JSONObject();
            entity.put("url", url);
            arr.add(entity);
        }
        String response = httpPostAccess(Constant.MAP_API_HOST + Constant.BATCH_REQUEST_URI, params);
        JSONArray resultArr = JSON.parseArray(response);
        int size = resultArr.size();
        List<PlaceResult> resultList = new ArrayList<PlaceResult>(size);
        for (int i = 0; i < size; i++) {
            JSONObject object = resultArr.getJSONObject(i);
            JSONObject result = object.getJSONObject("body");
            resultList.add(JSON.parseObject(JSON.toJSONString(result),PlaceResult.class));
        }
        return resultList;
    }
    /**
     * http get
     *
     * @param httpGet
     * @return response
     */
    private String httpGetAccess(HttpGet httpGet) {
        String response = "";
        for (int i = 0; i < 3; i++) {
            try {
                HttpClientUtils httpClient = new HttpClientUtils();
                response = httpClient.executeWithLog(httpGet, new AbstractResponseHandler<String>() {
                    @Override
                    public String handle(HttpEntity entity) throws IOException {
                        return EntityUtils.toString(entity);
                    }
                });
                break;
            } catch (Exception e) {
                LOGGER.error("IOException {} times.ex:{}", (i + 1), e.getMessage(), e);
            }
        }
        return response;
    }

    /**
     * http post
     *
     * @param url
     * @param params
     * @return
     */
    private String httpPostAccess(String url, JSONObject params) {
        String response = "";
        if (StringUtils.isBlank(url)) {
            LOGGER.error("methodURI is blank.");
            return response;
        }
        HttpPost httpPost = new HttpPost(url);
        if (params != null && params.size() > 0) {
            StringEntity entity = new StringEntity(params.toJSONString(),"UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(entity);
        }
        for (int i = 0; i < 3; i++) {
            try {
                HttpClientUtils httpClient = new HttpClientUtils();
                response = httpClient.executeWithLog(httpPost, new AbstractResponseHandler<String>() {
                    @Override
                    public String handle(HttpEntity entity) throws IOException {
                        return EntityUtils.toString(entity);
                    }
                });
                break;
            } catch (Exception e) {
                LOGGER.error("IOException.ex:{}", e.getMessage());
            }
        }
        return response;
    }

    public static void main(String[] args) {
//        MapService server = new MapService();
//        DistanceParam param = new DistanceParam();
//        param.setKey(Constant.KEY_CAR_LINE);
//        param.setOrigins("116.506218,40.006226");
//        param.setDestination("116.480665,39.996404");
//        DistanceResult result = server.getDistance(param);
//        LOGGER.info("------result:" + JSON.toJSONString(result));

//        PlaceParam placeParam = new PlaceParam();
//        placeParam.setKey(Constant.KEY_CAR_LINE);
//        placeParam.setCity("beijing");
//        placeParam.setKeywords("天安门");
//        PlaceResult placeResult = server.getPlaceInfo(placeParam);
//        LOGGER.info("------placeResult:" + JSON.toJSONString(placeResult));

        String location = "116.506218,40.006226";
        LOGGER.info("id-----" + location.hashCode());
    }
}
