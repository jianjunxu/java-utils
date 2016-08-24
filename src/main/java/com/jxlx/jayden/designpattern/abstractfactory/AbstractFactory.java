package com.jxlx.jayden.designpattern.abstractfactory;

import com.jxlx.jayden.designpattern.common.MyInterface;

/**
 * User : jianjun.xu
 * Date : 2016/8/24
 * Time : 13:54
 * Desc : 抽象工厂方法模式：创建多个工厂类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。
 */
public class AbstractFactory {
	public static void main(String[] args) {
		Provider provider = new MyFactoryOne();
		MyInterface myi = provider.produce();
		myi.print();
	}
}
