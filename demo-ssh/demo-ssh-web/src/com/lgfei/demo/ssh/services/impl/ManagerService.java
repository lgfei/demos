package com.lgfei.demo.ssh.services.impl;

import com.lgfei.demo.ssh.daos.IManagerDao;
import com.lgfei.demo.ssh.pojo.Manager;
import com.lgfei.demo.ssh.services.IManagerService;
import com.lgfei.demo.ssh.utils.StringUtil;

public class ManagerService implements IManagerService {

	private IManagerDao mgDao;
	
	public void setMgDao(IManagerDao mgDao){
		this.mgDao = mgDao;
	}
	
	@Override
	public Manager findOne(String no) {
		if(StringUtil.isNullOrEmpty(no)){
			return null;
		}
		return mgDao.findOne(no);
	}

}
