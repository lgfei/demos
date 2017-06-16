package com.lgfei.demo.ssh.pojo;

import java.util.Date;

/**
 * demo实体类
 * @author 龙国飞
 * 2014-8-24
 */
public class Demo {

	private Long demoId;
	private String demoName;
	private Date demoDate;
	public Long getDemoId() {
		return demoId;
	}
	public void setDemoId(Long demoId) {
		this.demoId = demoId;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public Date getDemoDate() {
		return demoDate;
	}
	public void setDemoDate(Date demoDate) {
		this.demoDate = demoDate;
	}

}
