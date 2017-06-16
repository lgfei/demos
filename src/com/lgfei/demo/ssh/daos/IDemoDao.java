package com.lgfei.demo.ssh.daos;

import java.util.List;

import com.lgfei.demo.ssh.pojo.Demo;

public interface IDemoDao {
	
	public List<?> findAll();
	
	public void save(Demo demo);
}
