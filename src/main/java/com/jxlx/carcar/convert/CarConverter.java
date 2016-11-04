package com.jxlx.carcar.convert;

import com.google.common.collect.Maps;
import com.jxlx.carcar.entity.params.DistanceParam;
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
}
