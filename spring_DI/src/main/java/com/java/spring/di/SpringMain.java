package com.java.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.java.general.Calculator;

public class SpringMain {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");

//		Calculator cal = (Calculator)ctx.getBean("calculator");
		Calculator cal = ctx.getBean("calculator",Calculator.class);
//		Calculator cal = ctx.getBean(Calculator.class);
		//클래스명은 모르지만 타입이 뭔지 알때 클래스타입만 달라고 요청하는 방법이야
	
		cal.sum(3, 5);
		cal.sum(3, 5, 10);
	
	
	}
}
