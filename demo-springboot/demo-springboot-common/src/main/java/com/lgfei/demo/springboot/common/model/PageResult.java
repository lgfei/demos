package com.lgfei.demo.springboot.common.model;

import java.util.List;

/**
 * 分页查询结果集
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PageResult<T>
{
    private int total;
    
    private List<T> resultList;
    
    public int getTotal()
    {
        return total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public List<T> getResultList()
    {
        return resultList;
    }
    
    public void setResultList(List<T> resultList)
    {
        this.resultList = resultList;
    }
}
