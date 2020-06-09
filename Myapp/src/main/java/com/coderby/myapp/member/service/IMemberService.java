package com.coderby.myapp.member.service;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberService {

	void insertMember(MemberVO member);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	
}
