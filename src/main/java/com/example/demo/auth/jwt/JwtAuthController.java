package com.example.demo.auth.jwt;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.auth.AjaxResponse;
import com.example.demo.exception.CustomExeption;
import com.example.demo.exception.CustomExeptionType;

@RestController
public class JwtAuthController {

	@Resource
	JwtAuthService jwtAuthService;
	
	@RequestMapping("/authentication")
	public AjaxResponse login(@RequestBody Map<String,String> map) {
		
		String userName = map.get("username");
		String password  = map.get("password");
		
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			return AjaxResponse.error(
					new CustomExeption(CustomExeptionType.USER_INPUT_ERROR, 
															"Username or Password error!")
							);
		}
		
		try {
			return AjaxResponse.succeed(jwtAuthService.login(userName, password));
		} catch (CustomExeption e) {
			return AjaxResponse.error(e);
		}
		
	}
	
	@RequestMapping("/refreshtoken")
	public AjaxResponse refresh(@RequestHeader("${jwt.header}")String token) {
		return AjaxResponse.succeed(this.jwtAuthService.refresh(token));
		
	}
	
	
	
}
