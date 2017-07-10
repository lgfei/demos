package com.lgfei.deom.springboot.common.enums;

/**
 * 角色枚举类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年7月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum Authors
{
    /**
     * 原创作者
     */
    LGFEI(0, "lgfei");
    
    /**
     * <默认构造函数>
     */
    Authors(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 作者id
     */
    private int id;
    
    /**
     * 作者名称
     */
    private String name;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
}
