package com.jxlx.carcar.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.convert.CarConverter;
import com.jxlx.carcar.entity.params.DirectionParam;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DirectionResult;
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
     * TODO 需改进批量查询 http://lbs.amap.com/api/webservice/guide/api/direction/#distance
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

    /**
     * 批量查两个位置距离
     *
     * @param params
     * @return
     */
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

    public List<DistanceResult> batchRequestDistance(List<DistanceParam> distanceParams) {
        List<DistanceResult> resultList = Lists.newArrayList();
        int size = distanceParams.size();
        List<DistanceParam> params = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            params.add(distanceParams.get(i));
            if (i > 0 && (i % 20 == 0 || i == size - 1)) {
                resultList.addAll(batchDistance(params));
                params.clear();
            }
        }
        return resultList;
    }

    /**
     * 批量距离查询 每次最多20个子查询
     *
     * @param distanceParams
     * @return
     */
    public List<DistanceResult> batchDistance(List<DistanceParam> distanceParams) {
        JSONObject params = new JSONObject();
        JSONArray arr = new JSONArray();
        params.put("ops", arr);
        for (DistanceParam distanceParam : distanceParams) {
            Map<String, String> map = CarConverter.convertDistanceParam(distanceParam);
            String url = NetWorkURL.toURL(Constant.DISTANCE_URI, map);
            JSONObject entity = new JSONObject();
            entity.put("url", url);
            arr.add(entity);
        }
        LOGGER.info("url:" + Constant.MAP_API_HOST + Constant.BATCH_REQUEST_URI);
        LOGGER.info("params:"+JSON.toJSONString(params));
        String response = httpPostAccess(Constant.MAP_API_HOST + Constant.BATCH_REQUEST_URI, params);
        JSONArray resultArr = JSON.parseArray(response);
        int size = resultArr.size();
        List<DistanceResult> resultList = new ArrayList<DistanceResult>(size);
        for (int i = 0; i < size; i++) {
            JSONObject object = resultArr.getJSONObject(i);
            JSONObject result = object.getJSONObject("body");
            resultList.add(JSON.parseObject(JSON.toJSONString(result), DistanceResult.class));
        }
        LOGGER.info(JSON.toJSONString(resultList));
        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setOriId(distanceParams.get(i).getOriId());
            resultList.get(i).setOriLocation(distanceParams.get(i).getOrigins());
            resultList.get(i).setDestId(distanceParams.get(i).getDestId());
            resultList.get(i).setDestLocation(distanceParams.get(i).getDestination());
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
     * 批量 获取位置信息
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
     * 批量获取位置信息 限制20个目的地
     *
     * http://lbs.amap.com/api/webservice/guide/api/batchrequest/
     */
    public List<PlaceResult> batchRequestPlaceInfo(List<PlaceParam> placeParams) {
        JSONObject params = new JSONObject();
        JSONArray arr = new JSONArray();
        params.put("ops", arr);
        for (PlaceParam placeParam : placeParams) {
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
            resultList.add(JSON.parseObject(JSON.toJSONString(result), PlaceResult.class));
        }
        LOGGER.info(JSON.toJSONString(resultList));
        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setPlaceId(placeParams.get(i).getPlaceId());
        }
        return resultList;
    }

    /**
     * 驾车规划API服务地址
     * http://lbs.amap.com/api/webservice/guide/api/direction/#driving
     */
    public DirectionResult drivingDirection(DirectionParam directionParam){
        Preconditions.checkNotNull(directionParam, "DirectionParam is null.");
        String methodURI = Constant.MAP_API_HOST + Constant.DRIVING_DIRECTION;
        Map<String, String> parameters = CarConverter.convertDirectionParam(directionParam);
        String url = NetWorkURL.toURL(methodURI, parameters);
        String response = httpGetAccess(new HttpGet(url));
        if (StringUtils.isBlank(response)) {
            LOGGER.info("response is blank.placeParam:{}", JSON.toJSONString(directionParam));
            return null;
        }
        DirectionResult result = JSON.parseObject(response, DirectionResult.class);
        if (result == null) {
            LOGGER.info("JSON.parseObject error.response:{}", JSON.toJSONString(response));
            return null;
        }
        result.setOriginId(directionParam.getOriginId());
        result.setDestId(directionParam.getDestId());
        result.setWaypointsId(directionParam.getWaypointsId());

        return result;
    }

    /**
     * 批量
     * TODO 需优化
     */
    public List<DirectionResult>  batchDrivingDirection(List<DirectionParam> params){
        List<DirectionResult> resultList = Lists.newArrayList();
        for (DirectionParam param:params){
            DirectionResult result = drivingDirection(param);
            resultList.add(result);
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
            StringEntity entity = new StringEntity(params.toJSONString(), Charsets.UTF_8);
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
                        return EntityUtils.toString(entity, Charsets.UTF_8);
                    }
                });
                break;
            } catch (Exception e) {
                LOGGER.error("IOException.ex:{}", e.getMessage());
            }
        }
        return response;
    }

//
//	public static void testCallBack(){
//
//	}
    public static void main(String[] args) {
        MapService server = new MapService();
//        DistanceParam param = new DistanceParam();
//        param.setKey(Constant.KEY_CAR_LINE);
//        param.setOrigins("116.357483,39.907234");
//        param.setDestination("116.434446,39.90816");
//        DistanceResult result = server.getDistance(param);
//        LOGGER.info("------result:" + JSON.toJSONString(result));

//        PlaceParam placeParam = new PlaceParam();
//        placeParam.setKey(Constant.KEY_CAR_LINE);
//        placeParam.setCity("beijing");
//        placeParam.setKeywords("天安门");
//        PlaceResult placeResult = server.getPlaceInfo(placeParam);
//        LOGGER.info("------placeResult:" + JSON.toJSONString(placeResult));

//        String location = "116.506218,40.006226";
//        LOGGER.info("id-----" + location.hashCode());

        DirectionParam directionParam = new DirectionParam();
        directionParam.setKey(Constant.KEY_CAR_LINE);
        directionParam.setOrigin("116.481028,39.989643");
        directionParam.setDestination("116.434446,39.90816");
        directionParam.setWaypoints("116.357483,39.907234");
        DirectionResult result = server.drivingDirection(directionParam);
        LOGGER.info("------DirectionResult:{}", JSON.toJSONString(result));
    }
}
