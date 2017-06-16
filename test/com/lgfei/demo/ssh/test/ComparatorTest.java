package com.lgfei.demo.ssh.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lgfei.demo.ssh.test.handle.PersonComparatorTest;
import com.lgfei.demo.ssh.test.vo.PersonVOTest;

public class ComparatorTest
{
    public static void main(String[] args)
    {
        PersonVOTest vo1 = new PersonVOTest(1, "小明", 12);
        PersonVOTest vo2 = new PersonVOTest(2, "小红", 14);
        PersonVOTest vo3 = new PersonVOTest(3, "小花", 11);
        
        List<PersonVOTest> voList = new ArrayList<PersonVOTest>();
        voList.add(vo1);
        voList.add(vo2);
        voList.add(vo3);
        
        System.out.println("原list:");
        printPersons(voList);
        
        Comparator<PersonVOTest> comparatorAsc = new PersonComparatorTest();
        Collections.sort(voList, comparatorAsc);
        
        System.out.println("升序排序后list:");
        printPersons(voList);
        
        Comparator<PersonVOTest> comparatorDesc = Collections.reverseOrder(comparatorAsc);
        Collections.sort(voList, comparatorDesc);
        
        System.out.println("降序排序后list:");
        printPersons(voList);
    }
    
    private static void printPersons(List<PersonVOTest> voList)
    {
        for (PersonVOTest vo : voList)
        {
            System.out.println(vo);
        }
    }
}
