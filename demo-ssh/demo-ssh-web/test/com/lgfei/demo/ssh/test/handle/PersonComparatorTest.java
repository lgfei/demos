package com.lgfei.demo.ssh.test.handle;

import java.util.Comparator;

import com.lgfei.demo.ssh.test.vo.PersonVOTest;

public class PersonComparatorTest implements Comparator<PersonVOTest>
{
    
    @Override
    public int compare(PersonVOTest o1, PersonVOTest o2)
    {
        return o1.getAge() - o2.getAge();
    }
    
}
