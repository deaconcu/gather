package com.jike.mobile.gather.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Timer implements ServletRequestAware{
	private Logger log = LoggerFactory.getLogger(Timer.class);
	private HttpServletRequest request;

	public Object total(ProceedingJoinPoint pjp) throws Throwable {
		String url = request.getRequestURI();
		log.info("action begin:" + url);

		StopWatch watch = new StopWatch();
		watch.start();
		Object result = pjp.proceed();
		watch.stop();
		
		log(pjp, watch);
		log.info("action end");
		return result;
	}
	
	public Object part(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();
		Object result = pjp.proceed();
		watch.stop();
		
		log(pjp, watch);
		return result;  
	}
	
	private void log(ProceedingJoinPoint pjp, StopWatch watch) {
		log.info("Time:" + watch.getTime() + " [" + pjp.getTarget().getClass() + "."  
				+ pjp.getSignature().getName() + "("+StringUtils.join(pjp.getArgs(),",")+")] ");  
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
