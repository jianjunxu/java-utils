package com.jxlx.jayden.singleton;

/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 18:03
 * Desc : 懒汉式，线程安全
 */
public class LazySingletonSafe {
	private static LazySingletonSafe instance;
	private LazySingletonSafe(){

	}
	/**
	 * 虽然做到了线程安全，并且解决了多实例的问题，但是它并不高效。
	 * 因为在任何时候只能有一个线程调用 getInstance() 方法。
	 * 但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时。
	 */
	public static synchronized LazySingletonSafe getInstance(){
		if(instance == null){
			instance = new LazySingletonSafe();
		}
		return instance;
	}
}
