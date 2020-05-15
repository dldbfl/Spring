package com.spring.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class 양치 {
	
	public void chikachikaProceed(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object result = joinPoint.proceed();
		System.out.println("양치하기");

	}
	
	public void chikachika(JoinPoint joinPoint) throws Throwable{
			
		System.out.println("양치하기");

	}
	
}
