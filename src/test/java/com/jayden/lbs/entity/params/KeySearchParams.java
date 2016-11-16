package com.jayden.lbs.entity.params;

import java.io.Serializable;

/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 10:18
 * Desc :
 */
public class KeySearchParams implements Serializable {

	private static final long serialVersionUID = 1577361091007353446L;
	/**
	 * 请求服务权限标识
	 */
	private String key;
	/**
	 * 规则： 多个关键字用“|”分割 TODO 这里限制单个关键字
	 */
	private String keywords;
	/**
	 * eg.北京/beijing/010/110000
	 */
	private String city;
	/**
	 * 仅返回指定城市数据
	 */
	private String citylimit = String.valueOf(true);
	/**
	 * 分页信息
	 */
	private String offset = "1";
	private String page = "1";

	/**
	 * 返回数据格式类型
	 */
	private String output = "JSON";
	private String extensions = "ALL";
	private String children = "0";

	/**
	 * 唯一标识 用来区分批量查询接口
	 * 与数据库中位置id一致
	 */
	private long id;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

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

	public String getCitylimit() {
		return citylimit;
	}

	public void setCitylimit(String citylimit) {
		this.citylimit = citylimit;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
