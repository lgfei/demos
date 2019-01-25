package com.lgfei.demo.activemq.springboot;

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Publisher {
	 
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
 
    @Autowired
    private Topic topic;
 
    // 每5s执行1次
    @Scheduled(fixedDelay = 5000)    
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.topic, "hello,springboot.activemq.topic");
    }

}

