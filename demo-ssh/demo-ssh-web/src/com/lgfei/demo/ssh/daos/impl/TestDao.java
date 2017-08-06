package com.lgfei.demo.ssh.daos.impl;

import java.util.List;

import com.lgfei.demo.ssh.daos.ITestDao;
import com.lgfei.demo.ssh.pojo.Test;
import com.lgfei.demo.ssh.utils.DBUtil;

public class TestDao implements ITestDao
{
    @Override
    public List<?> findAll()
    {
        return (List<?>)DBUtil.getHibernateTemplate().find("from Test");
    }
    
    @Override
    public void save(Test demo)
    {
        DBUtil.getHibernateTemplate().save(demo);
    }
    
}
