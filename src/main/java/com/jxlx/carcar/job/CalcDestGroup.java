package com.jxlx.carcar.job;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.entity.DistancePointsInfo;
import com.jxlx.carcar.entity.DistanceResDo;
import com.jxlx.carcar.entity.DistanceTransferResDo;
import com.jxlx.carcar.entity.PositionDo;
import com.jxlx.carcar.entity.params.DirectionParam;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DirectionResult;
import com.jxlx.carcar.entity.result.DistanceResult;
import com.jxlx.carcar.entity.result.PlaceResult;
import com.jxlx.carcar.mock.MockCoordinates;
import com.jxlx.carcar.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by jayden on 16/11/4.
 */
public class CalcDestGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalcDestGroup.class);


    public static void main(String[] args) {
        distanceTransfer();
//        List<String> descList = MockCoordinates.initDistance();
//        LOGGER.info("--------" + JSON.toJSONString(calTwoDistinceInfo(descList)));
    }

    /**
     * 计算两个目的地间的距离vs耗时
     *
     * @return
     */
    public static List<DistanceResDo> calTwoDistinceInfo(List<PositionDo> descList) {
        // 1 init
        Preconditions.checkArgument(!CollectionUtils.isEmpty(descList), "init data empty.");
        // 2 获取两点经纬度信息
        List<DistancePointsInfo> infos = getDestInfo(descList);
        // 3 计算每两点距离
        List<DistanceParam> distanceParams = Lists.transform(infos, new Function<DistancePointsInfo, DistanceParam>() {
            @Override
            public DistanceParam apply(DistancePointsInfo input) {
                DistanceParam param = new DistanceParam();
                param.setOrigins(input.getOriLocation());
                param.setDestination(input.getDestLocation());
                param.setKey(Constant.KEY_CAR_LINE);
                param.setOriId(input.getOriId());
                param.setDestId(input.getDestId());
                return param;
            }
        });
        MapService service = new MapService();
        List<DistanceResult> resultList = service.batchRequestDistance(distanceParams);
        List<DistanceResDo> dos = new ArrayList<DistanceResDo>();
        for (int i = 0; i < resultList.size(); i++) {
            DistanceResult input = resultList.get(i);
            //1
            DistanceResDo resDo = new DistanceResDo();
            resDo.setOriId(input.getOriId());
            resDo.setDestId(input.getDestId());
            resDo.setDistance(input.getResults().get(0).getDistance());
            resDo.setDuration(input.getResults().get(0).getDuration());
            resDo.setOriLocation(input.getOriLocation());
            resDo.setDestLocation(input.getDestLocation());
            dos.add(resDo);
            //2
            DistanceResDo resDo2 = new DistanceResDo();
            resDo2.setOriId(input.getDestId());
            resDo2.setDestId(input.getOriId());
            resDo2.setDistance(input.getResults().get(0).getDistance());
            resDo2.setDuration(input.getResults().get(0).getDuration());
            resDo2.setOriLocation(input.getOriLocation());
            resDo2.setDestLocation(input.getDestLocation());
            dos.add(resDo2);
        }
//        List<DistanceResDo> dos = Lists.transform(resultList, new Function<DistanceResult, DistanceResDo>() {
//            @Override
//            public DistanceResDo apply(DistanceResult input) {
//                DistanceResDo resDo = new DistanceResDo();
//                resDo.setOriId(input.getOriId());
//                resDo.setDestId(input.getDestId());
//                resDo.setDistance(input.getResults().get(0).getDistance());
//                resDo.setDuration(input.getResults().get(0).getDuration());
//                return resDo;
//            }
//        });
        return dos;
    }

    /**
     * 中转目的地
     *
     * @return
     */
    public static List<DistanceTransferResDo> distanceTransfer() {
        /** 1 初始数据准备 */
        /** 1.1 原始数据 */
        List<PositionDo> initData = MockCoordinates.initPosition();
        /** 1.2 距离数据 *之后做比较用 */
        List<DistanceResDo> distanceResDos = calTwoDistinceInfo(initData);
        Map<String, DistanceResDo> ids2resDo = Maps.newHashMap();
        for (DistanceResDo distanceResDo : distanceResDos) {
            String key = distanceResDo.getOriId() + "-" + distanceResDo.getDestId();
            ids2resDo.put(key, distanceResDo);
        }

        /** 2 组合目的地 */
        /** 2.1 一个中转点 起点为 1000,北京,北京首都国际机场 */
        String startId = "1000";
        List<String> destIds = Lists.transform(initData, new Function<PositionDo, String>() {
            @Override
            public String apply(PositionDo input) {
                return input.getPid();
            }
        });
        destIds.remove(startId); // 去掉起点
        List<DirectionParam> params = Lists.newArrayList();
        for (int i = 0; i < destIds.size(); i++) { //一个目的地
            String iKey = startId + "-" + destIds.get(i);
            DistanceResDo iDo = ids2resDo.get(iKey);
            for (int j = i + 1; j < destIds.size(); j++) { //另一个目的地
                String jKey = startId + "-" + destIds.get(j);
                DistanceResDo jDo = ids2resDo.get(jKey);
                DirectionParam directionParam = new DirectionParam();
                directionParam.setKey(Constant.KEY_CAR_LINE);
                if (Long.valueOf(iDo.getDistance()) > Long.valueOf(jDo.getDistance())) { // 判断起始点去哪个目的地远
                    directionParam.setOrigin(iDo.getOriLocation());
                    directionParam.setDestination(iDo.getDestLocation());
                    directionParam.setWaypoints(jDo.getDestLocation());
                    directionParam.setOriginId(iDo.getOriId());
                    directionParam.setDestId(iDo.getDestId());
                    directionParam.setWaypointsId(jDo.getDestId());
                } else {
                    directionParam.setOrigin(jDo.getOriLocation());
                    directionParam.setDestination(jDo.getDestLocation());
                    directionParam.setWaypoints(iDo.getDestLocation());
                    directionParam.setOriginId(jDo.getOriId());
                    directionParam.setDestId(jDo.getDestId());
                    directionParam.setWaypointsId(iDo.getDestId());
                }
                params.add(directionParam);
            }
        }
        MapService service = new MapService();
        List<DirectionResult> resultList = service.batchDrivingDirection(params);
        for (DirectionResult param : resultList) {
            LOGGER.info("~~~~~~~" + JSON.toJSONString(param));
        }
        /** 2.2 两个中转点 */
        /** 3 convert */
        return null;
    }

    /**
     * 获取目的地经纬度
     *
     * @param pdoList 目的地信息
     * @return
     */
    public static List<DistancePointsInfo> getDestInfo(List<PositionDo> pdoList) {
        List<DistancePointsInfo> infos = Lists.newArrayList();
        List<PlaceParam> dests = Lists.newArrayList();
        int totalSize = pdoList.size();
        for (int i = 0; i < totalSize; i++) {
            PositionDo iDest = pdoList.get(i);
            PlaceParam placeParam = new PlaceParam();
            placeParam.setPlaceId(iDest.getPid());
            placeParam.setCity(iDest.getPcity());
            placeParam.setKeywords(iDest.getPname());
            dests.add(placeParam);
            for (int j = i + 1; j < totalSize; j++) {
                DistancePointsInfo info = new DistancePointsInfo();
                PositionDo jDest = pdoList.get(j);
                info.setOriId(iDest.getPid());
                info.setOriDisCity(iDest.getPcity());
                info.setOriDisPoints(iDest.getPname());
                info.setDestId(jDest.getPid());
                info.setDestDisCity(jDest.getPcity());
                info.setDestDisPoints(jDest.getPname());
                infos.add(info);
            }
        }
        MapService service = new MapService();
        // 3 批量获取经纬度 dests
        List<PlaceResult> placeResults = service.batchRequestPlaceInfo(dests);
        Preconditions.checkArgument(!CollectionUtils.isEmpty(placeResults), "placeResults is empty.");
        Map<String, PlaceResult> pid2place = Maps.uniqueIndex(placeResults, new Function<PlaceResult, String>() {
            @Override
            public String apply(PlaceResult input) {
                return input.getPlaceId();
            }
        });
        for (DistancePointsInfo info : infos) {
            PlaceResult oriPlace = pid2place.get(info.getOriId());
            PlaceResult destPlace = pid2place.get(info.getDestId());
            info.setOriLocation(oriPlace.getPois().get(0).getLocation());
            info.setDestLocation(destPlace.getPois().get(0).getLocation());
        }
        return infos;
    }
}
