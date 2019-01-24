package com.lgfei.demo.activemq.simple;

public class ProducterTest {

	public static void main(String[] args) {
		Producter producter = new Producter();
		producter.init();
		// 创建5个生产者
		ProducterTest mq = new ProducterTest();
		new Thread(mq.new ProducterMq(producter)).start();
		new Thread(mq.new ProducterMq(producter)).start();
		new Thread(mq.new ProducterMq(producter)).start();
		new Thread(mq.new ProducterMq(producter)).start();
		new Thread(mq.new ProducterMq(producter)).start();
	}

	private class ProducterMq implements Runnable {
		Producter producter;

		public ProducterMq(Producter producter) {
			this.producter = producter;
		}

		//@Override
		public void run() {
			producter.sendMessage("simple.actviemq");
		}
	}
}
