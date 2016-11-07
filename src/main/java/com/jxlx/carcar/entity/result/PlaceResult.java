package com.jxlx.carcar.entity.result;

import com.jxlx.carcar.entity.PoiInfo;

import java.util.List;

/**
 * Created by jayden on 16/11/4.
 */
public class PlaceResult extends BaseResult {
    private static final long serialVersionUID = -7563322077293212816L;

    /** 搜索方案数目(最大值为1000) */
    private String count;
    /** 与placeParam中placeId对应 */
    private String placeId;
    private List<PoiInfo> pois;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<PoiInfo> getPois() {
        return pois;
    }

    public void setPois(List<PoiInfo> pois) {
        this.pois = pois;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "PlaceResult{" +
                "count='" + count + '\'' +
                ", pois=" + pois +
                '}';
    }
}
