package com.lgfei.demo.activemq.simple.common;

import org.apache.activemq.ActiveMQConnection;

public interface Constants {

	String USERNAME = ActiveMQConnection.DEFAULT_USER;
	
	String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
	String BROKEN_URL = "tcp://47.106.134.165:61616";
}
