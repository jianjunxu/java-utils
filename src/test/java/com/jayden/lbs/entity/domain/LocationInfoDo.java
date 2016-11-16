package com.jayden.lbs.entity.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 10:57
 * Desc : 对应db中location_info表
 */
public class LocationInfoDo implements Serializable {
	private static final long serialVersionUID = 7285146486509008184L;
	private long id;
	private String pCode;
	private String pName;
	private String cityCode;
	private String cityName;
	private String name;
	/**
	 * 经度
	 */
	private String positionLon;
	/**
	 * 纬度
	 */
	private String positionLat;
	private long outId;
	private int outType;
	private int isHub;
	private Date gmtCreated;
	private Date gmtModified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionLon() {
		return positionLon;
	}

	public void setPositionLon(String positionLon) {
		this.positionLon = positionLon;
	}

	public String getPositionLat() {
		return positionLat;
	}

	public void setPositionLat(String positionLat) {
		this.positionLat = positionLat;
	}

	public long getOutId() {
		return outId;
	}

	public void setOutId(long outId) {
		this.outId = outId;
	}

	public int getOutType() {
		return outType;
	}

	public void setOutType(int outType) {
		this.outType = outType;
	}

	public int getIsHub() {
		return isHub;
	}

	public void setIsHub(int isHub) {
		this.isHub = isHub;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
