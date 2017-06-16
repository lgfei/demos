package com.lgfei.demo.ssh.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.util.StringUtils;

public class Test {
	
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/app/moudle/action?data=1";
		String localIP = getLocalIP();
		String urlIP = getUrlIP(url);
		String urlUri = getUrlURI(url);
		System.out.println("localIP:"+localIP);
		System.out.println("urlIP:"+urlIP);
		System.out.println("urlUri:"+urlUri);
	}
	
    /**
     * 获取本机ip
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getLocalIP()
    {
        String localIP = null;
        try
        {
            localIP = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e)
        {
            System.out.println("get the localhost ip error occurred!");
        }
        return localIP;
    }
    
    /**
     * 内部接口调用时通过url解析其中的ip地址
     * <功能详细描述>
     * @param url
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getUrlIP(String url)
    {
        String ip = null;
        if (!StringUtils.isEmpty(url))
        {
            String[] arr = url.split("\\/");
            if (arr.length > 2)
            {
                ip = arr[2];
            }
            if (null != ip && ip.contains(":"))
            {
                ip = ip.split("\\:")[0];
            }
        }
        return ip;
    }
    
    /**
     * 获取url中的uri
     * <功能详细描述>
     * @param url
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getUrlURI(String url)
    {
        String uri = null;
        if (!StringUtils.isEmpty(url))
        {
            String[] arr = url.split("\\/", 4);
            if (arr.length == 4)
            {
            	uri = arr[3];
            }
            if (!StringUtils.isEmpty(uri) && uri.contains("?"))
            {
                uri = uri.split("\\?")[0];
            }
        }
        return null == uri ? "" : "/" + uri;
    }
}
