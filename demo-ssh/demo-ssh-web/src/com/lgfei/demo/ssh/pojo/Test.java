package com.lgfei.demo.ssh.pojo;

import java.util.Date;

/**
 * test实体类
 * @author 龙国飞
 * 2014-8-24
 */
public class Test {

	private Long id;
	private String no;
	private String name;
	private Date createTime;
	private Date updateTime;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getNo()
    {
        return no;
    }
    public void setNo(String no)
    {
        this.no = no;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public Date getUpdateTime()
    {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

}
