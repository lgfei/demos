package com.lgfei.demo.activemq.simple;

public class PublisherTest {

	public static void main(String[] args) {
		Publisher publisher = new Publisher();
		publisher.init();
		publisher.sendMessage("simple.activemq.topic");
	}

}
