package com.jxlx.jayden.singleton;

/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 18:27
 * Desc : 饿汉式 static final field
 */
public class HungrySingleton {
	/**
	 * 实例被声明成 static 和 final 变量
	 * 第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的
	 */
	private static final HungrySingleton instance = new HungrySingleton();
	private HungrySingleton(){}
	public static HungrySingleton getInstance(){
		return instance;
	}
}
