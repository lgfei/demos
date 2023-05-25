package com.lgfei.demo.springboot.test;

public class TempTest
{
    public static void main(String[] args)
    {
        Thread.currentThread().setName("anytime");
        throw new NullPointerException("无中生有");
    }
}
