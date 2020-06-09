package com.coderby.myapp.member.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.member.service.IMemberService;

@Controller
public class LoginController {

	@Autowired
	IMemberService memberService;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/loginCheck")
	public String loginCheck(@AuthenticationPrincipal Object mem, Model model, HttpSession session) {
		if(mem.equals("anonymousUser")) {
			model.addAttribute("message","아이디 또는 비밀번호가 다릅니다.");
			return "login";
		}else {
			MemberVO member = memberService.getMember((String)mem);
			session.setAttribute("userId", member.getUserId());
			session.setAttribute("auth", member.getAuth());
			session.setAttribute("startTime", LocalDateTime.now());
			String url = (String)session.getAttribute("url");
			System.out.println(url);
			if(url==null) {
				url = "hr/index";
			}
			session.removeAttribute("url");
			return "redirect:/"+url;
		}
	}
	
	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}
