package com.coderby.myapp.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		System.out.println("인터셉터 실행");
		System.out.println(session.getAttribute("url"));
		String contextName = request.getContextPath();
		String url = request.getRequestURI().replaceFirst(contextName, "");
		String param = request.getQueryString();
		if(!url.contains("login") && !url.contains("logout")) {
			System.out.println("너는 되고 있냐");
			session.setAttribute("url", url);
			session.setAttribute("param", param);
			System.out.println(session.getAttribute("url"));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
