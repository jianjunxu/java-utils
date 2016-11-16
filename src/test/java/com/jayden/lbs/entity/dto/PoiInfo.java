package com.jayden.lbs.entity.dto;

import java.io.Serializable;

/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 10:37
 * Desc :
 */
public class PoiInfo implements Serializable {
	private static final long serialVersionUID = -2273443476238965798L;
	private String name;
	private String location;
	private String pcode;
	private String pname;
	private String citycode;
	private String cityname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
}
