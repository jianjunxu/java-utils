package com.jxlx.jayden.singleton;

/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 18:06
 * Desc : 双重检验锁
 */
public class DoubleCheckedSingleton {
	private static DoubleCheckedSingleton instance;

	private DoubleCheckedSingleton() {
	}

	/**
	 * instance = new Singleton()是一个非原子操作
	 * 1 给 instance 分配内存
	 * 2 调用 Singleton 的构造函数来初始化成员变量
	 * 3 将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
	 * JVM 的即时编译器中存在指令重排序的优化。2和3顺序不能保证。最终的执行顺序可能是 1-2-3 也可能是 1-3-2
	 */
	public static DoubleCheckedSingleton getInstance() {
		if (instance == null) { //Single Checked
			synchronized (DoubleCheckedSingleton.class) {
				if (instance == null) { //Double Checked
					instance = new DoubleCheckedSingleton();
				}
			}
		}
		return instance;
	}
}
