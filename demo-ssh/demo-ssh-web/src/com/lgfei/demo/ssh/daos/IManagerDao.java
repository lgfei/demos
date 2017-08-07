package com.lgfei.demo.ssh.daos;

import com.lgfei.demo.ssh.pojo.Manager;

public interface IManagerDao {
	
	public Manager findOne(String mgName);

}
