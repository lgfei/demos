package com.lgfei.demo.ssh.pojo;

import java.util.Date;

/**
 * @author lgfei
 *
 */
public class Student extends Base
{
    
    private static final long serialVersionUID = 399851587215036041L;
    
    private String no;
    
    private String name;
    
    private Integer sex;
    
    private Date birthday;
    
    private String idcard;

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

    public Integer getSex()
    {
        return sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getIdcard()
    {
        return idcard;
    }

    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }
    
}
