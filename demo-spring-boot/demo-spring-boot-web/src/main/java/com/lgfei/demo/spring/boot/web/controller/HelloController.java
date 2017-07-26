package com.lgfei.demo.spring.boot.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo控制器
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
public class HelloController
{
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Map<String, Object> helloGet()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "Get");
        map.put("msg", "Hello World!");
        map.put("date", new Date());
        
        return map;
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Map<String, Object> helloPost()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "Post");
        map.put("msg", "Hello World!");
        map.put("date", new Date());
        
        return map;
    }
    
}
