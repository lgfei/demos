package com.lgfei.demo.spring.boot.core.service;

import java.util.List;

import com.lgfei.demo.spring.boot.common.model.PageResult;
import com.lgfei.demo.spring.boot.common.model.PageVO;
import com.lgfei.demo.spring.boot.common.model.UserVO;

/**
 * 用户模块Service接口
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface UserService
{
    /**
     * 查询全部
     * <功能详细描述>
     * @return 查询结果
     * @see [类、类#方法、类#成员]
     */
    List<UserVO> findAll();
    
    /**
     * 通过主键id单个查询
     * <功能详细描述>
     * @param id 主键id
     * @return 查询结果
     * @see [类、类#方法、类#成员]
     */
    UserVO findById(Long id);
    
    /**
     * 分页查询
     * <功能详细描述>
     * @param userVO 业务参数
     * @param pageVO 分页参数
     * @return 查询结果
     * @see [类、类#方法、类#成员]
     */
    PageResult<UserVO> find(UserVO userVO, PageVO pageVO);
}
