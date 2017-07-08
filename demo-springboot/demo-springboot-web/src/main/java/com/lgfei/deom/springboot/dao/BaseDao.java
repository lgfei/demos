package com.lgfei.deom.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lgfei.deom.springboot.model.PageVO;

/**
 * 通用的DAO层接口
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface BaseDao<T>
{
    /**
     * 插入
     * <功能详细描述>
     * @param paramVO 参数
     * @see [类、类#方法、类#成员]
     */
    void insert(T paramVO);
    
    /**
     * 删除
     * <功能详细描述>
     * @param id 主键id
     * @see [类、类#方法、类#成员]
     */
    void delete(Long id);
    
    /**
     * 修改
     * <功能详细描述>
     * @param paramVO 参数
     * @see [类、类#方法、类#成员]
     */
    void update(T paramVO);
    
    /**
     * 分页查询
     * <功能详细描述>
     * @param paramVO 业务参数
     * @param pageVO 分页参数
     * @return 查询结果
     * @see [类、类#方法、类#成员]
     */
    List<T> find(@Param("param") T paramVO, @Param("page") PageVO pageVO);
    
    /**
     * 查询全部
     * <功能详细描述>
     * @return 查询结果
     * @see [类、类#方法、类#成员]
     */
    List<T> findAll();
    
    /**
     * 通过主键id单个查询
     * <功能详细描述>
     * @param id 主键id
     * @return 查询结果
     * @see [类、类#方法、类#成员]
     */
    T findById(Long id);
}
