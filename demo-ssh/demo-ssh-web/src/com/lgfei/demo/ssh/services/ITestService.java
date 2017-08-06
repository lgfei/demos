package com.lgfei.demo.ssh.services;

import java.util.List;

import com.lgfei.demo.ssh.pojo.Test;

public interface ITestService {

	public List<?> findAll();
	
	public void save(Test demo);
}
