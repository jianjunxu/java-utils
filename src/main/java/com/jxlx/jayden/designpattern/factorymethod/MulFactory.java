package com.jxlx.jayden.designpattern.factorymethod;

import com.jxlx.jayden.designpattern.common.MyClassOne;
import com.jxlx.jayden.designpattern.common.MyClassTwo;
import com.jxlx.jayden.designpattern.common.MyInterface;

/**
 * User : jianjun.xu
 * Date : 2016/8/24
 * Time : 13:46
 * Desc : 多个工厂方法模式：对普通工厂方法模式的改进，多个工厂方法模式就是提供多个工厂方法，分别创建对象。
 */
public class MulFactory {
	public MyInterface produceOne() {
		return new MyClassOne();
	}

	public MyInterface produceTwo() {
		return new MyClassTwo();
	}

	public static void main(String[] args) {
		MulFactory factory = new MulFactory();
		MyInterface myi = factory.produceOne();
		myi.print();
	}
}
