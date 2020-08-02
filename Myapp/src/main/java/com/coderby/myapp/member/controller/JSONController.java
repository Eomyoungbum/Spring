package com.coderby.myapp.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderby.myapp.member.model.JSONVO;

@Controller
@RequestMapping("/json")
public class JSONController {

	@RequestMapping(value="/example", produces="application/text;charset=UTF-8")
	@ResponseBody
	public String example(String userId) {
		return "ajax 데이터 : "+userId;
	}
	
	@RequestMapping(value="/ex", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String exam(@RequestBody JSONVO jsonVO) {
		return "이름 : "+jsonVO.getName()+", 나이 : "+jsonVO.getAge();
	}
	
}
