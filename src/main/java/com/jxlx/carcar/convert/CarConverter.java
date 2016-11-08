package com.jxlx.carcar.convert;

import com.google.common.collect.Maps;
import com.jxlx.carcar.entity.params.DirectionParam;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * Created by jayden on 16/11/4.
 */
public class CarConverter {
    public static Map<String, String> convertDistanceParam(DistanceParam distanceParam) {
        Map<String, String> resMap = Maps.newHashMap();
        resMap.put("key", distanceParam.getKey());
        resMap.put("origins",distanceParam.getOrigins());
        resMap.put("destination",distanceParam.getDestination());
        return resMap;
    }

    public static Map<String, String> convertPlaceParam(PlaceParam placeParam){
        Map<String, String> resMap = Maps.newHashMap();
        resMap.put("key", placeParam.getKey());
        resMap.put("city", placeParam.getCity());
        resMap.put("keywords", placeParam.getKeywords());
        resMap.put("offset", String.valueOf(placeParam.getOffset()));
        resMap.put("page", String.valueOf(placeParam.getPage()));
        resMap.put("citylimit", String.valueOf(placeParam.isCitylimit()));
        return resMap;
    }

    public static Map<String, String> convertDirectionParam(DirectionParam directionParam){
        Map<String, String> resMap = Maps.newHashMap();
        resMap.put("key", directionParam.getKey());
        resMap.put("origin", directionParam.getOrigin());
        resMap.put("destination", directionParam.getDestination());
        resMap.put("strategy", String.valueOf(directionParam.getStrategy()));
        resMap.put("waypoints", String.valueOf(directionParam.getWaypoints()));
        return resMap;
    }
}
