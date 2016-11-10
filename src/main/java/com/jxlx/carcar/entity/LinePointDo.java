package com.jxlx.carcar.entity;

/**
 * User : jianjun.xu
 * Date : 2016/11/10
 * Time : 16:42
 * Desc :
 */
public class LinePointDo {
	/** id */
	private String id;
	/** 酒店名 */
	private String name;
	/** 经度 */
	private String lon;
	/** 纬度 */
	private String lat;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}
