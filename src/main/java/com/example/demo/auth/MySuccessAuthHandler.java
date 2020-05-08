package com.example.demo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class MySuccessAuthHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Value("${spring.mysecurity.loginType}")
	private String loginTypeString;
	
	private static ObjectMapper ObjectMapper = new ObjectMapper(); 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(this.loginTypeString.equals("JSON")) {
			response.setContentType("application/json;charset=utf-8");
			ResponseObj r = new ResponseObj("Login succeed", 1234);
			r.setIsOk(true);
			response.getWriter().write(ObjectMapper.writeValueAsString(r));
		}else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}
	
	
	
}
