package com.jxlx.jayden.designpattern.factorymethod;

import com.jxlx.jayden.designpattern.common.MyClassOne;
import com.jxlx.jayden.designpattern.common.MyClassTwo;
import com.jxlx.jayden.designpattern.common.MyInterface;

/**
 * User : jianjun.xu
 * Date : 2016/8/24
 * Time : 13:48
 * Desc : 静态工厂方法模式：将多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
 */
public class StaticFactory {
	public static MyInterface produceOne() {
		return new MyClassOne();
	}

	public static MyInterface produceTwo() {
		return new MyClassTwo();
	}

	public static void main(String[] args) {
		MyInterface myi = StaticFactory.produceOne();
		myi.print();
	}
}
