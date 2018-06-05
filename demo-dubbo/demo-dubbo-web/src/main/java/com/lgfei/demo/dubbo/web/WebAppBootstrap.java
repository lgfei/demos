package com.lgfei.demo.dubbo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppBootstrap {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebAppBootstrap.class);
        app.run(args);
	}
}
