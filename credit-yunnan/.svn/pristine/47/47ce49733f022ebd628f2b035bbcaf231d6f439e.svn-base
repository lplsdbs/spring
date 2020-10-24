package com.ccb.portal.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExceptionAdviceConfig {
	private static final Logger logger = LoggerFactory.getLogger("ExceptionAdviceConfig");

	@Pointcut("execution(* com.ccb.portal.advice.ExceptionAdvice.*(..))")
	public void exceptionLog() {
	}

	@Before("exceptionLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		if (joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] instanceof Exception) {
			logger.error("", ((Exception) joinPoint.getArgs()[0]));
		}

	}


}