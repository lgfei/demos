package com.lgfei.deom.springboot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfei.deom.springboot.dao.UserDao;
import com.lgfei.deom.springboot.model.PageResult;
import com.lgfei.deom.springboot.model.PageVO;
import com.lgfei.deom.springboot.model.UserVO;
import com.lgfei.deom.springboot.service.UserService;

/**
 * 用户模块Service接口实现
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class UserServiceImpl implements UserService
{
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<UserVO> findAll()
    {
        LOG.info("findAll start...");
        return userDao.findAll();
    }
    
    @Override
    public UserVO findById(Long id)
    {
        LOG.info("findById start...");
        return userDao.findById(id);
    }
    
    @Override
    public PageResult<UserVO> find(UserVO userVO, PageVO pageVO)
    {
        LOG.info("find start...");
        List<UserVO> userList = userDao.find(userVO, pageVO);
        
        PageResult<UserVO> result = new PageResult<>();
        result.setTotal(pageVO.getTotal());
        result.setResultList(userList);
        
        return result;
    }
    
}
