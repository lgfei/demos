package com.lgfei.demo.dubbo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lgfei.demo.dubbo.api.service.IDemoService;

@Controller
@RequestMapping("/web")
public class BaseController {

	//@Autowired
	@Reference(version = "1.0.0")
	private IDemoService demoService;
	
	@RequestMapping(value="/hello/{name}",method = RequestMethod.GET)
	@ResponseBody
	public String hello(@PathVariable String name) {
		return demoService.hello(name);
	}
	
}
