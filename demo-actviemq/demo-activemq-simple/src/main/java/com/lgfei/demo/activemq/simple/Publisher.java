package com.lgfei.demo.activemq.simple;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.lgfei.demo.activemq.simple.common.Constants;

public class Publisher {

	AtomicInteger count = new AtomicInteger(0);
	
	ConnectionFactory connectionFactory;
	
	Connection connection;
	
	Session session;
	
	ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();
	
	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(Constants.USERNAME,Constants.PASSWORD,Constants.BROKEN_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true,Session.SESSION_TRANSACTED);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
    public void sendMessage(String topicName){
        try {
            //创建一个主题
        	Topic topic = session.createTopic(topicName);
            //消息生产者
            MessageProducer messageProducer = null;
            if(threadLocal.get()!=null){
                messageProducer = threadLocal.get();
            }else{
                messageProducer = session.createProducer(topic);
                threadLocal.set(messageProducer);
            }
            
            // 每个生产者每秒生产一条消息
            while(true) {
            	Thread.sleep(1000);
                int num = count.getAndIncrement();
                //创建一条消息
                String msgContent = "publisher=" + Thread.currentThread().getName() + ",count=" + num;
                TextMessage msg = session.createTextMessage(msgContent);
                System.out.println("发布者发布的消息:" + msgContent);
                //发送消息
                messageProducer.send(msg);
                //提交事务
                session.commit();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
