package com.coderby.myapp.hello.util;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HelloTime {

	public static void printTime() {
		System.out.println(new java.util.Date());
	}
	
}
