package com.jxlx.jayden.singleton;

/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 18:39
 * Desc : 创建枚举默认就是线程安全的,而且还能防止反序列化导致重新创建新的对象
 */
public enum EnumSingleton {
	INSTANCE;
}
