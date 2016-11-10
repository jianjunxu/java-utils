package com.jxlx.carcar.convert;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jxlx.carcar.entity.DistanceResDo;
import com.jxlx.carcar.entity.DistanceTransferResDo;
import com.jxlx.carcar.entity.LinePointDo;
import com.jxlx.carcar.entity.PositionDo;
import com.jxlx.carcar.entity.params.DirectionParam;
import com.jxlx.carcar.entity.params.DistanceParam;
import com.jxlx.carcar.entity.params.PlaceParam;
import com.jxlx.carcar.entity.result.DirectionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by jayden on 16/11/4.
 */
public class CarConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CarConverter.class);

	public static Map<String, String> convertDistanceParam(DistanceParam distanceParam) {
		Map<String, String> resMap = Maps.newHashMap();
		resMap.put("key", distanceParam.getKey());
		resMap.put("origins", distanceParam.getOrigins());
		resMap.put("destination", distanceParam.getDestination());
		return resMap;
	}

	public static Map<String, String> convertPlaceParam(PlaceParam placeParam) {
		Map<String, String> resMap = Maps.newHashMap();
		resMap.put("key", placeParam.getKey());
		resMap.put("city", placeParam.getCity());
		resMap.put("keywords", placeParam.getKeywords());
		resMap.put("offset", String.valueOf(placeParam.getOffset()));
		resMap.put("page", String.valueOf(placeParam.getPage()));
		resMap.put("citylimit", String.valueOf(placeParam.isCitylimit()));
		return resMap;
	}

	public static Map<String, String> convertDirectionParam(DirectionParam directionParam) {
		Map<String, String> resMap = Maps.newHashMap();
		resMap.put("key", directionParam.getKey());
		resMap.put("origin", directionParam.getOrigin());
		resMap.put("destination", directionParam.getDestination());
		resMap.put("strategy", String.valueOf(directionParam.getStrategy()));
		resMap.put("waypoints", String.valueOf(directionParam.getWaypoints()));
		return resMap;
	}

	public static DistanceTransferResDo convertTransferDo(DirectionResult result) {
		DistanceTransferResDo resDo = new DistanceTransferResDo();
		resDo.setOriId(result.getOriginId());
		resDo.setDestId(result.getDestId());
		resDo.setTransferId(result.getWaypointsId());
		resDo.setDistance(result.getRoute().get(0).getPaths().get(0).getDistance());
		resDo.setDuration(result.getRoute().get(0).getPaths().get(0).getDuration());
		return resDo;
	}

	public static DistanceTransferResDo convertTransferDo2(DistanceResDo destResDo, DistanceResDo transResDo) {
		DistanceTransferResDo resDo = new DistanceTransferResDo();
		resDo.setOriId(destResDo.getOriId());
		resDo.setTransferId(destResDo.getDestId());
		resDo.setDestId(transResDo.getDestId());
		resDo.setDistance("" + (Long.valueOf(transResDo.getDistance()) + Long.valueOf(destResDo.getDistance())));
		resDo.setDuration("" + (Long.valueOf(transResDo.getDuration()) + Long.valueOf(destResDo.getDuration())));
		LOGGER.info("所有可组合中转点：{}", JSON.toJSONString(resDo));
		return resDo;
	}

	public static LinePointDo convertLinePointDo(PositionDo pDo){
		LinePointDo res = new LinePointDo();
		res.setId(pDo.getPid());
		res.setName(pDo.getPname());
		res.setLon(pDo.getLocation().split(",")[0]);
		res.setLat(pDo.getLocation().split(",")[1]);
		return res;
	}
}
