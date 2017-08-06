package com.lgfei.demo.ssh.daos;

import java.util.List;

import com.lgfei.demo.ssh.pojo.Test;

public interface ITestDao {
	
	public List<?> findAll();
	
	public void save(Test demo);
}
