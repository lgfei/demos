package com.lgfei.demo.ssh.services.impl;

import java.util.List;

import com.lgfei.demo.ssh.daos.IDemoDao;
import com.lgfei.demo.ssh.pojo.Demo;
import com.lgfei.demo.ssh.services.IDemoService;

public class DemoService implements IDemoService {
	
	private IDemoDao demoDao;
	
	public IDemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(IDemoDao demoDao) {
		this.demoDao = demoDao;
	}

	@Override
	public List<?> findAll() {
		return demoDao.findAll();
	}

	@Override
	public void save(Demo demo) {
		if(null == demo){
			return;
		}
		demoDao.save(demo);
	}
}
