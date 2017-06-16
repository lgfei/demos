package com.lgfei.demo.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport implements RequestAware{

	private static final long serialVersionUID = -4948492182481284495L;

	private Map<String, Object> request;
	
	@Override
	public void setRequest(Map<String, Object> req) {
		this.request = req;
	}
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public String hello() throws Exception{
		msg = "Hello World";
		request.put("page", "welcome");
		return Action.SUCCESS;
	}
	
	public String goHome() throws Exception{
		msg = "这是首页";
		request.put("page", "index");
		return Action.SUCCESS;
	}
}
