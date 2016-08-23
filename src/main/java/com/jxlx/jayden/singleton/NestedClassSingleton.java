package com.jxlx.jayden.singleton;


import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

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

	/**
	 * 枚举单例应用
	 */
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("aa","bb","cc");
		List<String> transList = Lists.transform(list, TOUpperCase.INSTANCE);
		System.out.println(list);
		System.out.println(transList);
	}

	private enum TOUpperCase implements Function<String,String> {
		INSTANCE;
		@Override
		public String apply(String input) {
			return input.toUpperCase();
		}
	}
}
