package com.jxlx.jayden.singleton;

/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 17:59
 * Desc : 懒汉式，线程不安全
 */
public class LazySingletonUnsafe {
	private static LazySingletonUnsafe instance;

	private LazySingletonUnsafe() {
	}

	public static LazySingletonUnsafe getInstance() {
		if (instance == null) { // 多个线程并行调用 可能会创建多个LazySingleton实例
			instance = new LazySingletonUnsafe();
		}
		return instance;
	}
}
