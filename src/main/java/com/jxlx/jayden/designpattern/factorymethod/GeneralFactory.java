package com.jxlx.jayden.designpattern.factorymethod;

import com.jxlx.jayden.designpattern.common.MyClassOne;
import com.jxlx.jayden.designpattern.common.MyClassTwo;
import com.jxlx.jayden.designpattern.common.MyInterface;

/**
 * User : jianjun.xu
 * Date : 2016/8/24
 * Time : 13:39
 * Desc : 普通工厂模式：建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 */
public class GeneralFactory {
	public MyInterface produce(String type) {
		if ("One".equalsIgnoreCase(type)) {
			return new MyClassOne();
		} else if ("Two".equalsIgnoreCase(type)) {
			return new MyClassTwo();
		} else {
			System.out.println("没有要找的类型");
			return null;
		}
	}

	public static void main(String[] args) {
		GeneralFactory factory = new GeneralFactory();
		MyInterface myi = factory.produce("One");
		myi.print();
	}
}
