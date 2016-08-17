package com.jxlx.jayden.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User : jianjun.xu
 * Date : 2016/8/17
 * Time : 16:03
 * Desc : 字符串处理：分割，连接，填充
 */
public class StringOper {
	/**
	 * 常用.连接器[Joiner]
	 * joiner实例总是不可变的。用来定义joiner目标语义的配置方法总会返回一个新的joiner实例。这使得joiner实例都是线程安全的，你可以将其定义为static final常量。
	 */
	public static void joinArr() {
		List<String> list = Lists.newArrayList("Harry", null, "Ron", "Hermione");
		String joinSkipNull = Joiner.on(",").skipNulls().join(list).toString();
		String joinUseForNull = Joiner.on(",").useForNull("null").join(list).toString();
		System.out.println("joinSkipNull:" + joinSkipNull + "\r\njoinUseForNull:" + joinUseForNull);
	}

	/**
	 * 拆分器[Splitter]
	 * Splitter可以被设置为按照任何模式(正则)、字符、字符串或字符匹配器拆分。
	 */
	public static void splitterStr() {
		String str = "Harry,null,,Ron,Hermione";
		Iterable<String> iterator = Splitter.on(",")
				.trimResults() // 移除结果字符串的前导空白和尾部空白
				.omitEmptyStrings() // 从结果中自动忽略空字符串
				.split(str);
		List<String> list = Lists.newArrayList(iterator);
		System.out.println("result:" + list);
	}

	public static void test(){

	}

	public static void main(String[] args) {
		splitterStr();
	}
}
