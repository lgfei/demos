package com.lgfei.demo.activemq.springboot;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class AppBootstrap
{
    @Bean
    public Queue queue() {
       return new ActiveMQQueue("springboot.activemq");
    }
    
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(AppBootstrap.class);
        app.run(args);
    }
}
