package com.lgfei.demo.springboot.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础业务实体类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseVO implements Serializable
{
    /**
     * 序列
     */
    private static final long serialVersionUID = 7826765341295199154L;
    
    /**
     * 主键id
     */
    private Long id;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
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
