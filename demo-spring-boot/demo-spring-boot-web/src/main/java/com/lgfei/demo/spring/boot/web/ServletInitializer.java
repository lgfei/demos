package com.lgfei.demo.spring.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * 注册启动类
 * 用war包方式部署时用到
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ServletInitializer extends SpringBootServletInitializer
{
    private static final Logger LOG = LoggerFactory.getLogger(ServletInitializer.class);
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        LOG.info("开始加载应用相关配置");
        return application.sources(Application.class);
    }
}
