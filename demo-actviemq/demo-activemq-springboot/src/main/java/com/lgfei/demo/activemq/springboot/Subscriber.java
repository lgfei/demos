package com.lgfei.demo.activemq.springboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {

    @JmsListener(destination = "springboot.activemq.topic")
    public void receiveTopic(String msg) {
        System.out.println("订阅者1获取的消息:" + msg);
    }
    
    @JmsListener(destination = "springboot.activemq.topic")
    public void receiveTopic2(String msg) {
        System.out.println("订阅者2获取的消息:" + msg);
    }
}
