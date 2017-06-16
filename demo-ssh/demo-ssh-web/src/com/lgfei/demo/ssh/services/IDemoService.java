package com.lgfei.demo.ssh.services;

import java.util.List;

import com.lgfei.demo.ssh.pojo.Demo;

public interface IDemoService {

	public List<?> findAll();
	
	public void save(Demo demo);
}
