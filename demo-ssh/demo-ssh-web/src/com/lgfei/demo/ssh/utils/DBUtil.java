package com.lgfei.demo.ssh.utils;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class DBUtil {

	private static HibernateTemplate ht;
	private static ApplicationContext ctx;
	
	public static HibernateTemplate getHibernateTemplate() {
		if(ht==null){
			ctx = new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");
			SessionFactory sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
			ht = new HibernateTemplate(sessionFactory);
		}
		return ht;
	}
}
