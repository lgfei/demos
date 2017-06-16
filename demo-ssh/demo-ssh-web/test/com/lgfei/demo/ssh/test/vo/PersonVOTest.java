package com.lgfei.demo.ssh.test.vo;

public class PersonVOTest
{
    private long id;
    
    private String name;
    
    private int age;
    
    public PersonVOTest(long id, String name, int age)
    {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
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
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    @Override
    public String toString()
    {
        return "PersonVOTest [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
    
}
