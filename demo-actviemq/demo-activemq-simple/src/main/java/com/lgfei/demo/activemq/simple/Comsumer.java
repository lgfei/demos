package com.lgfei.demo.activemq.simple;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.lgfei.demo.activemq.simple.common.Constants;

public class Comsumer {

	AtomicInteger count = new AtomicInteger();
	
    ConnectionFactory connectionFactory;

    Connection connection;

    Session session;

    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<MessageConsumer>();

    public void init(){
        try {
            connectionFactory = new ActiveMQConnectionFactory(Constants.USERNAME,Constants.PASSWORD,Constants.BROKEN_URL);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void getMessage(String disname){
        try {
            Queue queue = session.createQueue(disname);
            MessageConsumer consumer = null;

            if(threadLocal.get()!=null){
                consumer = threadLocal.get();
            }else{
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }
            
            // 每个消费者每10秒消费一条消息
            while(true) {
            	Thread.sleep(10000);
            	TextMessage msg = (TextMessage) consumer.receive();
                if(msg!=null) {
                    msg.acknowledge();
                    System.out.println("Consumer=" + Thread.currentThread().getName() + ",count=" + count.getAndIncrement());
                    System.out.println("获取的消息："+ msg.getText());
                }else {
                	break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
