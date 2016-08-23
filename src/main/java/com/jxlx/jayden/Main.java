package com.jxlx.jayden;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * User : jianjun.xu
 * Date : 2016/8/16
 * Time : 17:39
 * Desc :
 */
public class Main {

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("aa","bb","cc");
		List<String> transList = Lists.transform(list, new Function<String, String>() {
			@Override
			public String apply(String input) {
				return input.toUpperCase();
			}
		});
		System.out.println(list);
		System.out.println(transList);
	}
}
