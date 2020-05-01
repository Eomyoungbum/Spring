package com.coderby.myapp.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.coderby.myapp.hello.service.IDAO;

@Component
public class HelloController {

	@Autowired
	@Qualifier("byeDAO")
	IDAO dao;
	
	public void getMessageControl() {
		dao.getMessage();
	}
	
	public static void main(String[] args) {
		System.out.println("이거때문에 에러?");
	}
	
}
