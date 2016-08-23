package com.jxlx.jayden.singleton;

/**
 * User : jianjun.xu
 * Date : 2016/8/23
 * Time : 18:14
 * Desc :
 */
public class DoubleCheckedSingleton2 {
	/**
	 * 声明为volatile 禁止 instance=new DoubleCheckedSingleton2() 重排序
	 * 在 volatile 变量的赋值操作后面会有一个内存屏障（生成的汇编代码上），读操作不会被重排序到内存屏障之前
	 * 读操作必须在执行完写操作之后
	 * 注意：jdk 1.5以前的版本 volatile 也不能完全避免重排序
	 */
	private static volatile DoubleCheckedSingleton2 instance;
	private DoubleCheckedSingleton2(){

	}
	public static DoubleCheckedSingleton2 getInstance(){
		if(instance == null){
			synchronized (DoubleCheckedSingleton2.class) {
				if(instance == null){
					instance = new DoubleCheckedSingleton2();
				}
			}
		}
		return instance;
	}
}
