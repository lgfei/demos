package com.lgfei.demo.ssh.daos.impl;

import java.util.List;

import com.lgfei.demo.ssh.daos.IDemoDao;
import com.lgfei.demo.ssh.pojo.Demo;
import com.lgfei.demo.ssh.utils.DBUtil;

public class DemoDao implements IDemoDao {

	@Override
	public List<?> findAll() {
		return (List<?>) DBUtil.getHibernateTemplate().find("from Demo");
	}

	@Override
	public void save(Demo demo) {
		DBUtil.getHibernateTemplate().save(demo);
	}

}
