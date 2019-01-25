package com.lgfei.demo.activemq.simple;

public class ProducerTest {

	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.init();
		// 创建5个生产者
		ProducerTest mq = new ProducerTest();
		new Thread(mq.new ProducerMq(producer)).start();
		new Thread(mq.new ProducerMq(producer)).start();
		new Thread(mq.new ProducerMq(producer)).start();
		new Thread(mq.new ProducerMq(producer)).start();
		new Thread(mq.new ProducerMq(producer)).start();
	}

	private class ProducerMq implements Runnable {
		Producer producer;

		public ProducerMq(Producer producer) {
			this.producer = producer;
		}

		//@Override
		public void run() {
			producer.sendMessage("simple.actviemq.queue");
		}
	}
}
