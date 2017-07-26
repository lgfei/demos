package com.lgfei.demo.spring.boot.web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

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
@MapperScan("com.lgfei.demo.spring.boot.core.dao")
@ComponentScan(basePackages = {"com.lgfei.demo.spring.boot"})
public class Application
{
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    /**
     * http访问端口
     */
    public static final int HTTP_PORT = 8081;
    
    /**
     * https访问端口
     */
    public static final int HTTPS_PORT = 8443;
    
    /**
     * Spring内置tomcat服务启动入口
     * <功能详细描述>
     * @param args 参数
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        LOG.info("开始启动");
        SpringApplication.run(Application.class, args);
        LOG.info("启动成功");
    }
    
    /**
     * 将http请求重定向到https
     * <p>
     * 让我们的应用支持http是个好想法，此时需要重定向到https，但是不能同时在application.properties中同时配置两个connector，
     * 所以要以编程的方式配置http connector，然后重定向到https connector
     * </p>
     * @return servlet容器
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer()
    {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory()
        {
            @Override
            protected void postProcessContext(Context context)
            {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(HTTP_PORT);
        connector.setSecure(false);
        connector.setRedirectPort(HTTPS_PORT);
        
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
