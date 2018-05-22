package me.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����Controllerע�⣬���ע��ֻ��һ��value���ԣ�Ĭ��ֵΪ���ַ���
 * @author ���Ľ�
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
	public String value() default "";
}
