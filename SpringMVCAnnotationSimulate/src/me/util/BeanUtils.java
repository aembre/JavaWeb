package me.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
 * 对Java反射中操作的一些封装
 */
public class BeanUtils {

	/**
	 * 实例化一个class
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T instanceClass(Class<T> clazz) {
		if (!clazz.isInterface()) {
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 通过构造方法实例化
	 * @param constructor
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T instanceClass(Constructor<T> constructor, Object... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		makeAccessible(constructor);
		return constructor.newInstance(args);// 调用构造方法实例化
	}

	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		try {
			return clazz.getMethod(methodName, paramTypes);
		} catch (NoSuchMethodException e) {
			return findDeclaredMethod(clazz, methodName, paramTypes);//返回共有的方法
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, paramTypes);
		} catch (NoSuchMethodException e) {
			if(clazz.getSuperclass() != null) {
				return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
			}
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method[] findDeclaredMethods(Class<?> clazz) {
		return clazz.getDeclaredMethods();
	}
	
	public static Field[] findDeclaredFields(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}
	
	public static void makeAccessible(Constructor<?> constructor) {
		if ((!Modifier.isPublic(constructor.getModifiers())
				|| !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) && !constructor.isAccessible()) {
			constructor.setAccessible(true);// 如果是私有的 设置为true 使其可以访问
		}
	}
}
