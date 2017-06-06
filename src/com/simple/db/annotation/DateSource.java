package com.simple.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记该方法所指向的一系列数据查询操作是否为主�?
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateSource {
	/**
	 * 是否是主�?
	 * @return
	 */
	String value() default "defaultTargetDataSource";
	
}
