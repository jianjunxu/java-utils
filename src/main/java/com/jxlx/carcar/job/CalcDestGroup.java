package com.jxlx.carcar.job;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.entity.DistancePointsInfo;
import com.jxlx.carcar.entity.DistanceResDo;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DistanceResult;
import com.jxlx.carcar.entity.result.PlaceResult;
import com.jxlx.carcar.mock.MockCoordinates;
import com.jxlx.carcar.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


/**
 * Created by jayden on 16/11/4.
 */
public class CalcDestGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalcDestGroup.class);

    public static void main(String[] args) {
        //1 init
        List<String> descList = MockCoordinates.initDesc();
        //2 packaging required params
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
        List<PlaceResult> placeResults = service.batchGetPlaceInfo(dests);
        Map<String, PlaceResult> name2place = Maps.uniqueIndex(placeResults, new Function<PlaceResult, String>() {
            @Override
            public String apply(PlaceResult input) {
                return input.getPlaceId();
            }
        });
        for (DistancePointsInfo info :infos) {
            info.setOriLocation(name2place.get(info.getOriId()).getPois().get(0).getLocation());
            info.setDestLocation(name2place.get(info.getDestId()).getPois().get(0).getLocation());
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
        List<DistanceResult> resultList = service.batchGetDistance(distanceParams);
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
        LOGGER.info("--------" + JSON.toJSONString(dos));
    }
}
