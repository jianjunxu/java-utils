package com.jxlx.jayden.singleton;


/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 18:29
 * Desc : 静态内部类 static nested class **推荐使用
 */
public class NestedClassSingleton {
	private static class SingletonHolder {
		private static final NestedClassSingleton instance = new NestedClassSingleton();
	}
	private NestedClassSingleton(){}
	public static NestedClassSingleton getInstance(){
		return SingletonHolder.instance;
	}
}
