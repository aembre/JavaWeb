package me.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开发RequestMapping注解，用于定义请求路径，这个注解只有一个value属性，默认值为空字符串
 * @author 张文杰
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {

	public String value() default "";
}
