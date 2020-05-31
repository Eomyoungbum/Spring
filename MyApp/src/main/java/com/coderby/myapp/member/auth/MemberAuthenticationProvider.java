package com.coderby.myapp.member.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.member.service.IMemberService;

@Component
public class MemberAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	IMemberService memberService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		String userId = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		MemberVO member = null;
		if(password==null||password.equals("")) {
			return null;
		}
		if(password.equals(memberService.getPassword(userId))) {
			member = memberService.getMember(userId);
			roles.add(new SimpleGrantedAuthority(member.getAuth()));
		}else {
			return null;
		}
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, password,roles);
		result.setDetails(member);
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
