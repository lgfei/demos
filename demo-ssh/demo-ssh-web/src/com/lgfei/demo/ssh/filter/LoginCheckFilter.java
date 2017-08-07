package com.lgfei.demo.ssh.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lgfei.demo.ssh.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class LoginCheckFilter implements Filter{
	private final static String SEPARATOR = ",";
	
	private String ignoreUrls;
	private String ignoreTypes;
	
	private List<String> ignoreUrlList = new ArrayList<String>();
	private List<String> ignoreTypeList = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LoginFilter init...");
		ignoreUrls = filterConfig.getInitParameter("ignoreUrls");
		System.out.println("ignoreUrls:"+ignoreUrls);
		ignoreUrlList = StringUtil.strToList(ignoreUrls, SEPARATOR);

		ignoreTypes = filterConfig.getInitParameter("ignoreTypes");
		System.out.println("ignoreTypes:"+ignoreTypes);
		ignoreTypeList = StringUtil.strToList(ignoreTypes, SEPARATOR);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		 System.out.println("LoginFilter doFilter...");
		 HttpServletRequest request = (HttpServletRequest) req;      
         HttpServletResponse response = (HttpServletResponse) res;
         String url = request.getServletPath(); 
         System.out.println("url:"+url);
         String suffix = url.substring(url.lastIndexOf(".")+1);
         //属于白名单的url直接通过
         if(StringUtil.isNullOrEmpty(url) || ignoreUrlList.contains(url)){
        	 chain.doFilter(req, res);
		     return;
         }
         if(ignoreTypeList.contains(suffix)){
        	 chain.doFilter(req, res);
		     return;
         }
         String loginMgName = (String)ActionContext.getContext().getSession().get("loginMgName");
         System.out.println("loginMgName:"+loginMgName);
	     if(StringUtil.isNullOrEmpty(loginMgName)){
	    	 String contextPath = request.getContextPath();
	    	 response.sendRedirect(contextPath+"/login.jsp");
	    	 return;
	     }
	     chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		System.out.println("LoginFilter destroy...");
		ignoreUrls = null;
		ignoreUrlList = null;
		ignoreTypes = null;
		ignoreTypeList = null;
	}

}
