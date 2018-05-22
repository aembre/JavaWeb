package me.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储方法的访问路径
 * @author 张文杰
 * 
 */
public class RequestMapingMap {

	private static Map<String, Class<?>> requestMap = new HashMap<>();
	
	public static Class<?> getClassName(String path){
		return requestMap.get(path);
	}
	
	public static void put(String path, Class<?> className) {
		requestMap.put(path, className);
	}
	
	public static Map<String, Class<?>> getRequestMap(){
		return requestMap;
	}
}
