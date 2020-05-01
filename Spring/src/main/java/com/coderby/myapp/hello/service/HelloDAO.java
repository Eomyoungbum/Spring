package com.coderby.myapp.hello.service;

import org.springframework.stereotype.Component;

import com.coderby.myapp.hello.util.HelloTime;

@Component
public class HelloDAO implements IDAO{

	public void getMessage() {
		System.out.println("헬로서비스");
	}
	
}
