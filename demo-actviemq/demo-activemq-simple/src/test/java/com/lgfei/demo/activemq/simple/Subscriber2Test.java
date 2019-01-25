package com.lgfei.demo.activemq.simple;

public class Subscriber2Test {

	public static void main(String[] args) {
		Subscriber2 subscriber2 = new Subscriber2();
		subscriber2.init();
		subscriber2.getMessage("simple.activemq.topic");
	}

}
