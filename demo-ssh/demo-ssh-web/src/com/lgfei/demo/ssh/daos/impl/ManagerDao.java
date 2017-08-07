package com.lgfei.demo.ssh.daos.impl;

import java.util.List;

import com.lgfei.demo.ssh.daos.IManagerDao;
import com.lgfei.demo.ssh.pojo.Manager;
import com.lgfei.demo.ssh.utils.CollectionUtil;
import com.lgfei.demo.ssh.utils.DBUtil;

public class ManagerDao implements IManagerDao
{
    @SuppressWarnings("unchecked")
    @Override
    public Manager findOne(String no)
    {
        List<Object> queryList = (List<Object>)DBUtil.getHibernateTemplate().find("from Manager t where t.no = ?", no);
        if (CollectionUtil.isNullOrEmpty(queryList))
        {
            return null;
        }
        return (Manager)queryList.get(0);
    }
    
}
