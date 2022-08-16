package com.pinmarket.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
	
	//throwing = "exception"은 exception이라는 변수명의 타입에 맞는 객체를 던져줘라! 라는 의미가 된다. || execution(* com.pinmarket.service.product.*.*(..))
	@AfterThrowing(value = "execution(* com.pinmarket.service.auction.*.*(..)) || execution(* com.pinmarket.service.product.*.*(..))",throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		
		log.error("<< 여기는 aop afterThrowing >>");
		log.error("joinPoint toString : "+joinPoint.toString());
		log.error("joinPoint Signature : "+joinPoint.getSignature());
		log.error("joinPoint Target : "+joinPoint.getTarget());
		for(int i=0;i<joinPoint.getArgs().length;i++) {
			log.error("joinPoint Args : "+joinPoint.getArgs()[i]);
		}
		log.error("예외 메시지 : "+exception.getMessage());
		log.error("예외 메시지2 : "+exception);
	}
	
	/*@Before("execution(* com.pinmarket.service.*.*.*(..))")
	public void before(JoinPoint joinPoint) {
		log.info("<< 여기는 aop before >> ");
		Object [] args1 = joinPoint.getArgs();
		
		for(int i=0;i<args1.length;i++) {
			log.info("joinPoint.getArgs(); : "+args1[i]);
		}
		log.info("joinPoint.getTarget() : "+joinPoint.getTarget());
	}
	*/
}
