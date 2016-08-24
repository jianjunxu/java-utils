package com.jxlx.jayden.designpattern.abstractfactory;

import com.jxlx.jayden.designpattern.common.MyClassTwo;
import com.jxlx.jayden.designpattern.common.MyInterface;

/**
 * User : jianjun.xu
 * Date : 2016/8/24
 * Time : 13:56
 * Desc :
 */
public class MyFactoryTwo implements Provider {
	@Override
	public MyInterface produce() {
		return new MyClassTwo();
	}
}
