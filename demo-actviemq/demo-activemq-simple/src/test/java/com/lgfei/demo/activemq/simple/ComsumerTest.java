package com.lgfei.demo.activemq.simple;

public class ComsumerTest {
	
    public static void main(String[] args){
        Comsumer comsumer = new Comsumer();
        comsumer.init();
        // 创建10个消费者
        ComsumerTest mq = new ComsumerTest();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
        new Thread(mq.new ConsumerMq(comsumer)).start();
    }

    private class ConsumerMq implements Runnable{
        Comsumer comsumer;
        
        public ConsumerMq(Comsumer comsumer){
            this.comsumer = comsumer;
        }

        //@Override
        public void run() {
        	comsumer.getMessage("simple.actviemq.queue");
        }
    }
}