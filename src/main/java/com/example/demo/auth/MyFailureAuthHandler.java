package com.example.demo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class MyFailureAuthHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Value("${spring.mysecurity.loginType}")
	private String loginTypeString;
	
	private static ObjectMapper ObjectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(this.loginTypeString.equals("JSON")) {
			response.setContentType("application/json;charset=utf-8");
			ResponseObj r = new ResponseObj("Login faild", 1234);
			r.setIsOk(false);
			response.getWriter().write(ObjectMapper.writeValueAsString(r));
		}else {
			super.onAuthenticationFailure(request, response, exception);
		}
		
	} 

	
	
}
