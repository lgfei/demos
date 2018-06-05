package com.lgfei.demo.dubbo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceAppBootstrap {

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServiceAppBootstrap.class);
        app.run(args);
	}
}
