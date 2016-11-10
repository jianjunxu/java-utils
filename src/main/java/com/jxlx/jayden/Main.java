package com.jxlx.jayden;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.jxlx.carcar.mock.MockCoordinates;

/**
 * User : jianjun.xu
 * Date : 2016/8/16
 * Time : 17:39
 * Desc :
 */
public class Main {

	public static void main(String[] args) {
//		List<String> list = Lists.newArrayList("aa","bb","cc");
//		List<String> transList = Lists.transform(list, TOUpperCase.INSTANCE);
//		System.out.println(list);
//		System.out.println(transList);
//		System.out.println((1 << 16) - 1);
//
		System.out.println(JSON.toJSONString(MockCoordinates.initPosition()));
	}

	private enum TOUpperCase implements Function<String, String> {
		INSTANCE;

		@Override
		public String apply(String input) {
			return input.toUpperCase();
		}
	}
}
