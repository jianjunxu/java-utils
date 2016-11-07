package com.jxlx.carcar.entity.params;

/**
 * Created by jayden on 16/11/4.
 */
public class PlaceParam extends BaseParam {
    private static final long serialVersionUID = -3976365745340555612L;

    /** 查询关键字 规则：多个关键字用“|”分割 */
    private String keywords;
    /** 查询城市 可选值：城市中文、中文全拼、citycode、adcode 如：北京/beijing/010/110000 */
    private String city;
    /** 仅返回指定城市数据 可选值：true/false 缺省:false */
    private boolean citylimit;
    private String placeId;
    private int offset = 1;
    private int page = 1;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isCitylimit() {
        return citylimit;
    }

    public void setCitylimit(boolean citylimit) {
        this.citylimit = citylimit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPage() {
        return page;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
