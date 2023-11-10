package com.lgfei.demo.oauth2.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class DemoOauth2AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2AuthApplication.class, args);
	}

}
