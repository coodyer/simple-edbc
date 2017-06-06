package com.simple.db.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import com.simple.db.annotation.DateSource;
import com.simple.db.util.AspectUtil;

public class DBAspect {
	/**
	 * ���ݿ����ӿ���
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.simple.db.annotation.DateSource)")
	public Object dbMonitor(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {
			sw.start(pjp.getSignature().toShortString());
			Signature signature = pjp.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			DateSource handle = method.getAnnotation(DateSource.class);
			AspectUtil.writeDBTemplate(handle.value());
			return pjp.proceed();
		} finally {
			AspectUtil.minusDBTemplate();
			sw.stop();
		}
	}
}
