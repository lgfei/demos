package com.lgfei.demo.ssh.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	public static boolean isEqual(String a,String b){
		if(a != null){
			return a.equals(b);
		}else if(b != null){
			return b.equals(a);
		}else{
			return true;
		}
	}
	
	public static boolean isNullOrEmpty(String str){
		return null == str ? true : isEqual(str, "");
	}

	public static List<String> strToList(String srcStr,String sperator){
		List<String> strList = new ArrayList<String>();
		if(isNullOrEmpty(srcStr) || isNullOrEmpty(sperator)){
			return strList;
		}
		String [] arr = srcStr.split(sperator);
		for (String str : arr) {
			strList.add(str);
		}
		return strList;
	}
}
