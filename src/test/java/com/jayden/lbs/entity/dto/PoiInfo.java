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
}
