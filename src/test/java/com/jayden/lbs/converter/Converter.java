package com.jayden.lbs.converter;

import com.jayden.lbs.entity.domain.LocationInfoDo;
import com.jayden.lbs.entity.dto.PoiInfo;
import com.jayden.lbs.entity.result.KeySearchResult;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 14:21
 * Desc :
 */
public class Converter {
	public static List<LocationInfoDo> convertLocationInfo(List<KeySearchResult> ksResults) {
		if (CollectionUtils.isEmpty(ksResults)) {
			return null;
		}
		List<LocationInfoDo> locationInfoDos = new ArrayList<LocationInfoDo>(ksResults.size());
		Date now = new Date();
		for (KeySearchResult ks : ksResults) {
			PoiInfo poiInfo = CollectionUtils.isEmpty(ks.getPois()) ? null : ks.getPois().get(0);
			if (poiInfo != null) {
				LocationInfoDo liDo = new LocationInfoDo();
				liDo.setId(ks.getId());
				liDo.setCityCode(poiInfo.getCitycode());
				liDo.setCityName(poiInfo.getCityname());
				liDo.setpCode(poiInfo.getPcode());
				liDo.setpName(poiInfo.getPname());
				liDo.setName(poiInfo.getName());
				String[] pos = poiInfo.getLocation().split(",");
				if (pos.length == 2) {
					liDo.setPositionLon(pos[0]);
					liDo.setPositionLat(pos[1]);
				}
				liDo.setGmtCreated(now);
				liDo.setGmtModified(now);
			}
		}
		return locationInfoDos;
	}
}
