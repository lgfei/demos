package com.lgfei.deom.springboot.model;

/**
 * 用户信息实体类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserVO extends BaseVO
{
    /**
     * 序列
     */
    private static final long serialVersionUID = 3743692990323659130L;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号
     */
    private String mobilephone;
    
    /**
     * 性别
     */
    private Integer gender;
    
    /**
     * 状态
     */
    private Integer state;
    
    /**
     * 审核状态
     */
    private Integer verifyState;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getMobilephone()
    {
        return mobilephone;
    }
    
    public void setMobilephone(String mobilephone)
    {
        this.mobilephone = mobilephone;
    }
    
    public Integer getGender()
    {
        return gender;
    }
    
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }
    
    public Integer getState()
    {
        return state;
    }
    
    public void setState(Integer state)
    {
        this.state = state;
    }
    
    public Integer getVerifyState()
    {
        return verifyState;
    }
    
    public void setVerifyState(Integer verifyState)
    {
        this.verifyState = verifyState;
    }
    
}
