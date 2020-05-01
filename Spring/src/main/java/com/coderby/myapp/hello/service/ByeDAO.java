package com.coderby.myapp.hello.service;

import org.springframework.stereotype.Component;

@Component
public class ByeDAO implements IDAO{

	public void getMessage() {
		System.out.println("바이서비스");
	}
	
}
