package com.lgfei.demo.dubbo.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lgfei.demo.dubbo.api.service.IDemoService;

//注册为 Dubbo 服务
@Service
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0")
public class Demoservice implements IDemoService {

	@Override
	public String hello(String name) {
		StringBuffer sb = new StringBuffer()
				.append(new Date().toString())
				.append(" : ")
				.append("hello,")
				.append(name)
				.append('!');
		System.out.println(sb);
		return sb.toString();
	}

}
