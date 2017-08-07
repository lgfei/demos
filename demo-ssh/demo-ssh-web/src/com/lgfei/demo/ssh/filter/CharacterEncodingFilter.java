package com.lgfei.demo.ssh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	private String encoding = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("CharacterEncodingFilter init...");
		encoding = filterConfig.getInitParameter("encoding");
		System.out.println("encoding:"+encoding);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("CharacterEncodingFilter doFilter...");
		if(encoding!=null){
			//设置request字符编码
			request.setCharacterEncoding(encoding);
            //设置response字符编码
			response.setContentType("text/html;charset="+encoding);
        }
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("CharacterEncodingFilter destroy...");
		encoding = null;
	}
}
