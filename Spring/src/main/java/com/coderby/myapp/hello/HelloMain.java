package com.coderby.myapp.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.coderby.myapp.hello.controller.HelloController;

public class HelloMain {

	public static void main(String[] args) {
		AbstractApplicationContext con = new GenericXmlApplicationContext("app-config.xml");
		HelloController control = con.getBean(HelloController.class);
		control.getMessageControl();
		con.close();
	}

}
