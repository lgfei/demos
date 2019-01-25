package com.lgfei.demo.activemq.springboot;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppBootstrap
{
    @Bean
    public Queue queue() {
       return new ActiveMQQueue("springboot.activemq.queue");
    }
    
    @Bean
    public Topic topic() {
       return new ActiveMQTopic("springboot.activemq.topic");
    }
    
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(AppBootstrap.class);
        app.run(args);
    }
}
