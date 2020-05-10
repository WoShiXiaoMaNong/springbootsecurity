package com.example.demo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class MySuccessAuthHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Value("${spring.mysecurity.loginType}")
	private String loginTypeString;
	
	@Autowired
	MyUserDetailsServiceMapper mmDetailsServiceMapper;
	
	private static ObjectMapper ObjectMapper = new ObjectMapper(); 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(this.loginTypeString.equals("JSON")) {
			MyUserDetails s = mmDetailsServiceMapper.findByUserName("user1");
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			ResponseObj r = new ResponseObj("Login succeed " + s, 1234);
			r.setIsOk(true);
			response.getWriter().write(ObjectMapper.writeValueAsString(r));
			response.getWriter().flush();
			response.getWriter().close();
		}else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}
	
	
	
}
