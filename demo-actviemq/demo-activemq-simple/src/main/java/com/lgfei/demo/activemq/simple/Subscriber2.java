package com.lgfei.demo.activemq.simple;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.lgfei.demo.activemq.simple.common.Constants;

public class Subscriber2 {

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

    public void getMessage(String topicName){
        try {
        	Topic topic = session.createTopic(topicName);
            MessageConsumer consumer = null;

            if(threadLocal.get()!=null){
                consumer = threadLocal.get();
            }else{
                consumer = session.createConsumer(topic);
                // 注册消息监听
                consumer.setMessageListener(new Listener2());
                threadLocal.set(consumer);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}


class Listener2 implements MessageListener{

	public void onMessage(Message message) {
        try {
            System.out.println("订阅者2收到的消息："+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
	}

}
