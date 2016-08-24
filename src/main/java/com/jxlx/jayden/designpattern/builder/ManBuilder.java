package com.jxlx.jayden.designpattern.builder;

/**
 * 建造者模式：是将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 建造者模式通常包括下面几个角色：
 * （1）Builder：给出一个抽象接口，以规范产品对象的各个组成成分的建造。这个接口规定要实现复杂对象的哪些部分的创建，并不涉及具体的对象部件的创建。
 * （2）ConcreteBuilder：实现Builder接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建。 在建造过程完成后，提供产品的实例。
 * （3）Director：调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。
 * （4）Product：要创建的复杂对象。
 */
public class ManBuilder implements PersonBuilder {

	Person person;

	public ManBuilder() {
		person = new Person();
	}

	public void buildBody() {
		person.setBody("建造男人的身体");
	}

	public void buildFoot() {
		person.setFoot("建造男人的脚");
	}

	public void buildHead() {
		person.setHead("建造男人的头");
	}

	public Person buildPerson() {
		return person;
	}

	public static void main(String[] args) {
		PersonDirector pd = new PersonDirector();
		Person person = pd.constructPerson(new ManBuilder());
		System.out.println(person.getBody());
		System.out.println(person.getFoot());
		System.out.println(person.getHead());
	}
}