package com.lgfei.deom.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 执行后就可以访问了，不用再部署到web容器中
 * 
 * @author  lgfei
 * @version  [版本号, 2017年6月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SpringBootApplication
@MapperScan("com.lgfei.deom.springboot.dao")
public class Application
{
    private final static Logger LOG = LoggerFactory.getLogger(Application.class); 
    
    public static void main(String[] args)
    {
        LOG.info("开始启动");
        SpringApplication.run(Application.class, args);
        LOG.info("启动成功");
    }
}
