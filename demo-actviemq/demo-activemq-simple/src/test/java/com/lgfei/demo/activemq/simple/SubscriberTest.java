package com.lgfei.demo.activemq.simple;

public class SubscriberTest {

	public static void main(String[] args) {
		Subscriber subscriber = new Subscriber();
		subscriber.init();
		subscriber.getMessage("simple.activemq.topic");
	}

}
