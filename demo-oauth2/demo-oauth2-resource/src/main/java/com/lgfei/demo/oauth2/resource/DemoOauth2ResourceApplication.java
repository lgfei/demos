package com.lgfei.demo.oauth2.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class DemoOauth2ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2ResourceApplication.class, args);
	}

}
