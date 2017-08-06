package com.lgfei.demo.ssh.services.impl;

import java.util.List;

import com.lgfei.demo.ssh.daos.ITestDao;
import com.lgfei.demo.ssh.pojo.Test;
import com.lgfei.demo.ssh.services.ITestService;

public class TestService implements ITestService {
	
	private ITestDao testDao;
	
	public ITestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(ITestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public List<?> findAll() {
		return testDao.findAll();
	}

	@Override
	public void save(Test test) {
		if(null == test){
			return;
		}
		testDao.save(test);
	}
}
