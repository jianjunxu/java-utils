package com.jxlx.jayden.designpattern.builder;

public interface PersonBuilder {
	void buildHead();

	void buildBody();

	void buildFoot();

	Person buildPerson();
}