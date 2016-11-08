package com.jxlx.carcar.job;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.entity.DistancePointsInfo;
import com.jxlx.carcar.entity.DistanceResDo;
import com.jxlx.carcar.entity.DistanceTransferResDo;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DistanceResult;
import com.jxlx.carcar.entity.result.PlaceResult;
import com.jxlx.carcar.mock.MockCoordinates;
import com.jxlx.carcar.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


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
    public static List<DistanceResDo> calTwoDistinceInfo(List<String> descList) {
        // 1 init
        Preconditions.checkArgument(!CollectionUtils.isEmpty(descList), "init data empty.");
        // 2 packaging required params
        final List<DistancePointsInfo> infos = Lists.newArrayList();
        List<PlaceParam> dests = Lists.newArrayList();
        int totalSize = descList.size();
        for (int i = 0; i < totalSize; i++) {
            String[] iDest = descList.get(i).split(",");
            PlaceParam placeParam = new PlaceParam();
            placeParam.setPlaceId(iDest[0]);
            placeParam.setCity(iDest[1]);
            placeParam.setKeywords(iDest[2]);
            dests.add(placeParam);
            for (int j = i + 1; j < totalSize; j++) {
                DistancePointsInfo info = new DistancePointsInfo();
                String[] jDest = descList.get(j).split(",");
                info.setOriId(iDest[0]);
                info.setOriDisCity(iDest[1]);
                info.setOriDisPoints(iDest[2]);
                info.setDestId(jDest[0]);
                info.setDestDisCity(jDest[1]);
                info.setDestDisPoints(jDest[2]);
                infos.add(info);
            }
        }
        MapService service = new MapService();
        // 3 批量获取经纬度 dests
        List<PlaceResult> placeResults = service.batchRequestPlaceInfo(dests);
        Preconditions.checkArgument(!CollectionUtils.isEmpty(placeResults), "placeResults is empty.");
        Map<String, PlaceResult> name2place = Maps.uniqueIndex(placeResults, new Function<PlaceResult, String>() {
            @Override
            public String apply(PlaceResult input) {
                return input.getPlaceId();
            }
        });
        for (DistancePointsInfo info : infos) {
            PlaceResult oriPlace = name2place.get(info.getOriId());
            PlaceResult destPlace = name2place.get(info.getDestId());
            info.setOriLocation(oriPlace.getPois().get(0).getLocation());
            info.setDestLocation(destPlace.getPois().get(0).getLocation());
        }
        // 4 计算距离 infos
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
        List<DistanceResult> resultList = service.batchRequestDistance(distanceParams);
        List<DistanceResDo> dos = Lists.transform(resultList, new Function<DistanceResult, DistanceResDo>() {
            @Override
            public DistanceResDo apply(DistanceResult input) {
                DistanceResDo resDo = new DistanceResDo();
                resDo.setOriId(input.getOriId());
                resDo.setDestId(input.getDestId());
                resDo.setDistance(input.getResults().get(0).getDistance());
                resDo.setDuration(input.getResults().get(0).getDuration());
                return resDo;
            }
        });
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
        String startPoint = MockCoordinates.initDistanceTransfer();
        List<String> destList = MockCoordinates.initDistance();
        List<String> all = Lists.newArrayList();
        all.addAll(destList);
        all.add(startPoint);
        /** 1.2 距离数据 *之后做比较用 */
        List<DistanceResDo> distanceResDos = calTwoDistinceInfo(all);
        Map<String, Map<String, DistanceResDo>> dist2dist2do = Maps.newHashMap();
        for (DistanceResDo distanceResDo : distanceResDos) {
            Map<String, DistanceResDo> distOri2do = dist2dist2do.get(distanceResDo.getOriId());
            if (CollectionUtils.isEmpty(distOri2do)) {
                distOri2do = Maps.newHashMap();
                dist2dist2do.put(distanceResDo.getOriId(), distOri2do);
            }
            distOri2do.put(distanceResDo.getDestId(), distanceResDo);

            Map<String, DistanceResDo> distDest2do = dist2dist2do.get(distanceResDo.getDestId());
            if (CollectionUtils.isEmpty(distDest2do)) {
                distDest2do = Maps.newHashMap();
                dist2dist2do.put(distanceResDo.getDestId(), distDest2do);
            }
            distDest2do.put(distanceResDo.getOriId(), distanceResDo);
        }
        /** 2 组合目的地 */
        /** 2.1 一个中转点 起点为 1000,北京,北京首都国际机场 */
        for (int i = 0; i < destList.size(); i++) { //选作中转点
            for (int j = i+1;j<destList.size(); j++){ //选作终点

            }
        }
        /** 2.2 两个中转点 */

        return null;
    }
}
