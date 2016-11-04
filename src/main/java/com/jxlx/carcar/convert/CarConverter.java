package com.jxlx.carcar.convert;

import com.google.common.collect.Maps;
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
}
