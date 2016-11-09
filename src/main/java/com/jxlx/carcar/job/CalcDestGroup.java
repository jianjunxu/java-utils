package com.jxlx.carcar.job;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.convert.CarConverter;
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


/**
 * Created by jayden on 16/11/4.
 */
public class CalcDestGroup {
	private static final Logger LOGGER = LoggerFactory.getLogger(CalcDestGroup.class);
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
	private static long MAX_DISTANCE = 5000;

	static {
		distanceTransfer();
	}

	public static void main(String[] args) {
		List<DistanceResDo> distanceDos = twoPosCache.get(twoPosCacheKey);
		List<DistanceTransferResDo> transferDos = transferCache.get(transferCacheKey);
		System.out.println("哈哈哈");
//        List<String> descList = MockCoordinates.initDistance();
//        LOGGER.info("--------" + JSON.toJSONString(calTwoDistinceInfo(descList)));
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
			String destId = key.split("-")[1];
			DistanceResDo res = key2distance.get(oriId+"-"+destId);
			if (resDo != null && Long.valueOf(resDo.getDistance()) - Long.valueOf(res.getDistance()) <= MAX_DISTANCE) {
				LOGGER.info("顺路(中转)-----" + JSON.toJSONString(resDo));
			}
		}
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
			/** 两点位置信息互换记录 */
			//1
			DistanceResDo resDo = new DistanceResDo();
			resDo.setOriId(input.getOriId());
			resDo.setDestId(input.getDestId());
			resDo.setDistance(input.getResults().get(0).getDistance());
			resDo.setDuration(input.getResults().get(0).getDuration());
			resDo.setOriLocation(input.getOriLocation());
			resDo.setDestLocation(input.getDestLocation());
			dos.add(resDo);
			LOGGER.info("两点距离计算结果(正)：{}", JSON.toJSONString(resDo));
			//2
			DistanceResDo resDo2 = new DistanceResDo();
			resDo2.setOriId(input.getDestId());
			resDo2.setDestId(input.getOriId());
			resDo2.setDistance(input.getResults().get(0).getDistance());
			resDo2.setDuration(input.getResults().get(0).getDuration());
			resDo2.setOriLocation(input.getDestLocation());
			resDo2.setDestLocation(input.getOriLocation());
			dos.add(resDo2);
			LOGGER.info("两点距离计算结果(反)：{}", JSON.toJSONString(resDo2));
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
		//-----------
		twoPosCache.put(twoPosCacheKey, distanceResDos);
		//------------
		Map<String, DistanceResDo> ids2resDo = Maps.newHashMap();
		/** key对应两点距离信息 key为oriId-destId */
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
			/** 机场到目的地i */
			DistanceResDo iDo = ids2resDo.get(iKey);
			for (int j = i + 1; j < destIds.size(); j++) { //另一个目的地
				String jKey = startId + "-" + destIds.get(j);
				/** 机场到目的地j */
				DistanceResDo jDo = ids2resDo.get(jKey);
				/** 中转路线参数准备 */
				DirectionParam directionParam = new DirectionParam();
				directionParam.setKey(Constant.KEY_CAR_LINE);
				if (Long.valueOf(iDo.getDistance()) > Long.valueOf(jDo.getDistance())) { // 判断起始点去哪个目的地远
					/** 目的地i距离远 */
					directionParam.setOrigin(iDo.getOriLocation());//起点为start
					directionParam.setDestination(iDo.getDestLocation()); // 目的地为i的地点
					directionParam.setWaypoints(jDo.getDestLocation());//中转点为j目的地
					directionParam.setOriginId(iDo.getOriId());
					directionParam.setDestId(iDo.getDestId());
					directionParam.setWaypointsId(jDo.getDestId());
				} else {
					/** 目的地j距离远 */
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
		List<DistanceTransferResDo> resDos = new ArrayList<DistanceTransferResDo>(resultList.size());
		for (DirectionResult result : resultList) {
			DistanceTransferResDo resDo = CarConverter.convertTransferDo(result);
			LOGGER.info("所有中转路线（一个中转点）：{}", JSON.toJSONString(resDo));
			resDos.add(resDo);
		}
		/** 2.2 两个中转点 */
		/** 3 convert List<DistanceTransferResDo> */

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
		boolean isGetInfo = false; // 是否通过高德获取经纬度信息
		List<DistancePointsInfo> infos = Lists.newArrayList();
		List<PlaceParam> dests = Lists.newArrayList();
		int totalSize = pdoList.size();
		for (int i = 0; i < totalSize; i++) {
			/** 经纬度信息参数 */
			PositionDo iDest = pdoList.get(i);
			LOGGER.info("获取到的目的地信息：{}", JSON.toJSONString(iDest));
			/////////////
			if (!isGetInfo) {
				PlaceParam placeParam = new PlaceParam();
				placeParam.setPlaceId(iDest.getPid());
				placeParam.setCity(iDest.getPcity());
				placeParam.setKeywords(iDest.getPname());
				dests.add(placeParam);
			}
			//////////////
			/** 组合需要计算的目的地距离（两两组合,只记一次） */
			for (int j = i + 1; j < totalSize; j++) {
				DistancePointsInfo info = new DistancePointsInfo();
				PositionDo jDest = pdoList.get(j);
				info.setOriId(iDest.getPid());
				info.setOriDisCity(iDest.getPcity());
				info.setOriDisPoints(iDest.getPname());
				info.setDestId(jDest.getPid());
				info.setDestDisCity(jDest.getPcity());
				info.setDestDisPoints(jDest.getPname());
				if (!isGetInfo) {
					info.setOriLocation(iDest.getLocation());
					info.setDestLocation(jDest.getLocation());
				}
				infos.add(info);
				LOGGER.info("所有目的地两两组合(只记一次)情况：{}", JSON.toJSONString(info));
			}
		}
		if (!isGetInfo) {
			return infos;
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
		/** 赋值经纬度 */
		for (DistancePointsInfo info : infos) {
			PlaceResult oriPlace = pid2place.get(info.getOriId());
			PlaceResult destPlace = pid2place.get(info.getDestId());
			info.setOriLocation(oriPlace.getPois().get(0).getLocation());
			info.setDestLocation(destPlace.getPois().get(0).getLocation());
		}
		return infos;
	}
}
