package com.lgfei.demo.ssh.utils;

import java.util.Collection;

public class CollectionUtil {

	public static boolean isNullOrEmpty(Collection<?> coll){
		if(null == coll){
			return true;
		}else{
			if(coll.isEmpty()){
				return true;
			}
		}
		return false;
	}
}
