package com.jxlx.carcar.job;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.convert.CarConverter;
import com.jxlx.carcar.entity.DistancePointsInfo;
import com.jxlx.carcar.entity.DistanceResDo;
import com.jxlx.carcar.entity.DistanceTransferResDo;
import com.jxlx.carcar.entity.PositionDo;
import com.jxlx.carcar.entity.params.DirectionParam;
import com.jxlx.carcar.entity.result.DirectionResult;
import com.jxlx.carcar.mock.MockCoordinates;
import com.jxlx.carcar.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by jayden on 16/11/4.
 */
public class CalcDestGroup2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalcDestGroup2.class);
	private static final String twoPosCacheKey = "twoPosCache";
	private static final String transferCacheKey = "transferCache";
	/**
	 * 缓存两点距离
	 **/
	private final static Map<String, List<DistanceResDo>> twoPosCache = Maps.newHashMap();
	/**
	 * 缓存中转路线
	 */
	private final static Map<String, List<DistanceTransferResDo>> transferCache = Maps.newHashMap();
	private static long MAX_DISTANCE = 2000;

	static {
		distanceTransfer();
	}

	public static void main(String[] args) {
		List<DistanceResDo> distanceDos = twoPosCache.get(twoPosCacheKey);
		List<DistanceTransferResDo> transferDos = transferCache.get(transferCacheKey);
		/** key:destId1-destId2 为方便获取数据 */
		Map<String, DistanceResDo> key2distance = Maps.uniqueIndex(distanceDos, new Function<DistanceResDo, String>() {
			@Override
			public String apply(DistanceResDo input) {
				return input.getOriId() + "-" + input.getDestId();
			}
		});
		List<String> initData = MockCoordinates.initQueryData();
		List<String> idList = Lists.newArrayList();
		List<String> idList2 = Lists.newArrayList();
		for (int i = 0; i < initData.size(); i++) {
			for (int j = i + 1; j < initData.size(); j++) {
				idList.add(initData.get(i) + "-" + initData.get(j));
				idList.add(initData.get(j) + "-" + initData.get(i));
				idList2.add(initData.get(i) + "-" + initData.get(j));
				idList2.add(initData.get(j) + "-" + initData.get(i));
			}
		}
		/** 两点 */
		for (String id : idList) {
			DistanceResDo resDo = key2distance.get(id);
			if (resDo != null && Long.valueOf(resDo.getDistance()) <= MAX_DISTANCE) {
				LOGGER.info("顺路(目的地距离近)-----" + JSON.toJSONString(resDo));
			}
		}

		///////////////////
		/** key:oriId-tranId-destId */
		Map<String, DistanceTransferResDo> key2transfer = Maps.uniqueIndex(transferDos, new Function<DistanceTransferResDo, String>() {
			@Override
			public String apply(DistanceTransferResDo input) {
				return input.getOriId() + "-" + input.getTransferId() + "-" + input.getDestId();
			}
		});
		/** 三点 */
		String oriId = "1000";
		for (String id : idList2) {
			String key = oriId+"-"+id;
			DistanceTransferResDo resDo = key2transfer.get(key);
			String destId = id.split("-")[1];
			DistanceResDo res = key2distance.get(oriId+"-"+destId);
			if (resDo != null && (Long.valueOf(resDo.getDistance()) - Long.valueOf(res.getDistance()) <= MAX_DISTANCE)) {
				LOGGER.info("顺路(中转)-----" + JSON.toJSONString(resDo));
			}
		}
	}

	/**
	 * 计算两个目的地间的距离vs耗时
	 *
	 * @return
	 */
	public static List<DistanceResDo> calTwoDisInfo(List<PositionDo> descList) {
		// 2 获取两点经纬度信息
		List<DistancePointsInfo> infoList = getDestInfo(descList);
		/** 计算两点距离参数准备 */
		List<DirectionParam> disParams = Lists.transform(infoList, new Function<DistancePointsInfo, DirectionParam>() {
			@Override
			public DirectionParam apply(DistancePointsInfo distince) {
				DirectionParam param = new DirectionParam();
				param.setOriginId(distince.getOriId());
				param.setOrigin(distince.getOriLocation());
				param.setDestId(distince.getDestId());
				param.setDestination(distince.getDestLocation());
				param.setStrategy("2");
				param.setKey(Constant.KEY_CAR_LINE);
				return param;
			}
		});
		MapService service = new MapService();
		List<DirectionResult> resultList = service.batchDrivingDirection(disParams);
		List<DistanceResDo> dos = new ArrayList<DistanceResDo>();
		for (int i = 0; i < resultList.size(); i++) {
			DirectionResult input = resultList.get(i);
			DistanceResDo resDo = new DistanceResDo();
			resDo.setOriId(input.getOriginId());
			resDo.setDestId(input.getDestId());
			resDo.setDistance(input.getRoute().get(0).getPaths().get(0).getDistance());
			resDo.setDuration(input.getRoute().get(0).getPaths().get(0).getDuration());
			dos.add(resDo);
			LOGGER.info("两点距离计算结果：{}", JSON.toJSONString(resDo));
		}
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
		List<DistanceResDo> distanceResDos = calTwoDisInfo(initData);
		/** 缓存计算好的数据 */
		twoPosCache.put(twoPosCacheKey, distanceResDos);
		Map<String, DistanceResDo> ids2resDo = Maps.newHashMap();
		/** key对应两点距离信息 key为oriId-destId */
		for (DistanceResDo distanceResDo : distanceResDos) {
			String key = distanceResDo.getOriId() + "-" + distanceResDo.getDestId();
			ids2resDo.put(key, distanceResDo);
		}

		/** 2 组合目的地 */
		/** 2.1 一个中转点 起点为 1000,北京,北京首都国际机场 */
		String startId = "1000";
		//所有目的地id
		List<String> destIds = Lists.transform(initData, new Function<PositionDo, String>() {
			@Override
			public String apply(PositionDo input) {
				return input.getPid();
			}
		});
		destIds.remove(startId); // 去掉起点
		/** 计算所有可能中转组合 机场作为起点 */
		List<DistanceTransferResDo> resDos = Lists.newArrayList();
		for (int i = 0; i < destIds.size(); i++) { //一个目的地
			String oriIKey = startId + "-" + destIds.get(i);
			for (int j = i + 1; j < destIds.size(); j++) { //另一个目的地
				// 需要比较哪个目的地作为中转点
				String oriJKey = startId + "-" + destIds.get(j);
				DistanceResDo iResDo = ids2resDo.get(oriIKey);
				DistanceResDo jResDo = ids2resDo.get(oriJKey);
				boolean iIsTrans = Long.valueOf(iResDo.getDistance()) >= Long.valueOf(jResDo.getDistance());
				if(iIsTrans){ // i距离机场远 j作为中转点
					//j到i的距离
					DistanceResDo j2i = ids2resDo.get(destIds.get(j) + "-" + destIds.get(i));
					// 机场到j j到i
					resDos.add(CarConverter.convertTransferDo2(jResDo, j2i));
				} else {
					DistanceResDo i2j = ids2resDo.get(destIds.get(i) + "-" + destIds.get(j));
					resDos.add(CarConverter.convertTransferDo2(iResDo, i2j));
				}
			}
		}

		transferCache.put(transferCacheKey, resDos);

		return resDos;
	}

	/**
	 * 获取目的地经纬度
	 * 之后要存一张位置信息表把各个目的地的经纬度等信息保存下来，就不用每次都去高德查
	 *
	 * @param pdoList 目的地信息
	 * @return
	 */
	public static List<DistancePointsInfo> getDestInfo(List<PositionDo> pdoList) {
		List<DistancePointsInfo> infos = Lists.newArrayList();
		int totalSize = pdoList.size();
		for (int i = 0; i < totalSize; i++) {
			/** 经纬度信息参数 */
			PositionDo iDest = pdoList.get(i);
			/** 组合需要计算的目的地距离 */
			for (int j = 0; j < totalSize; j++) {
				if (i != j) {
					DistancePointsInfo info = new DistancePointsInfo();
					PositionDo jDest = pdoList.get(j);
					info.setOriId(iDest.getPid());
					info.setOriDisCity(iDest.getPcity());
					info.setOriDisPoints(iDest.getPname());
					info.setOriLocation(iDest.getLocation());
					info.setDestId(jDest.getPid());
					info.setDestDisCity(jDest.getPcity());
					info.setDestDisPoints(jDest.getPname());
					info.setDestLocation(jDest.getLocation());
					infos.add(info);
					LOGGER.info("所有目的地两两组合情况（未计算距离）：{}", JSON.toJSONString(info));
				}
			}
		}
		return infos;
	}
}
