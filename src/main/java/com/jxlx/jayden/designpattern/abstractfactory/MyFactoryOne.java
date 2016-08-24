package com.jxlx.jayden.designpattern.abstractfactory;

import com.jxlx.jayden.designpattern.common.MyClassOne;
import com.jxlx.jayden.designpattern.common.MyInterface;

/**
 * User : jianjun.xu
 * Date : 2016/8/24
 * Time : 13:55
 * Desc :
 */
public class MyFactoryOne implements Provider {
	@Override
	public MyInterface produce() {
		return new MyClassOne();
	}
}
