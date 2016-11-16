package com.jayden.lbs.entity.result;

import com.jayden.lbs.entity.dto.PoiInfo;

import java.io.Serializable;
import java.util.List;

/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 10:18
 * Desc :
 */
public class KeySearchResult implements Serializable {
	private static final long serialVersionUID = 292280520562493744L;
	/**
	 * 结果状态值，值为0或1
	 * 0：请求失败；1：请求成功
	 */
	private int status;
	/**
	 * 返回状态说明
	 * status为0时，info返回错误原因，否则返回“OK”.
	 * http://lbs.amap.com/api/webservice/guide/tools/info/
	 */
	private String info;
	/**
	 * 详情
	 */
	private List<PoiInfo> pois;

	/**
	 * 唯一标识 用来区分批量查询接口
	 * 与数据库中位置id一致
	 */
	private long id;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<PoiInfo> getPois() {
		return pois;
	}

	public void setPois(List<PoiInfo> pois) {
		this.pois = pois;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
