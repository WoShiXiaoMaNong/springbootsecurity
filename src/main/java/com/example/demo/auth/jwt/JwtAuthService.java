package com.example.demo.auth.jwt;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomExeption;
import com.example.demo.exception.CustomExeptionType;

@Service
public class JwtAuthService {
	
	@Resource(name = BeanIds.AUTHENTICATION_MANAGER)
	AuthenticationManager authenticationManager;
	
	@Resource
	UserDetailsService myUserDetailsService;
	
	@Resource
	JwtTokenUtil jwtTokenUtil;
	/**
	 * 登錄換取Token
	 * @return
	 */
	public String login(String username, String password) throws CustomExeption{
		UsernamePasswordAuthenticationToken upToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			Authentication authentication = authenticationManager.authenticate(upToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			throw new CustomExeption(CustomExeptionType.USER_INPUT_ERROR, "Username or password error!");
		}
		
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(username)	;
		
		return jwtTokenUtil.generateToken(userDetails);
	}

	/**
	 * 刷新Token
	 * @param token
	 * @return
	 */
	public String refresh(String oldToken) {
		if( !jwtTokenUtil.isTokenExpired(oldToken)) {
			return jwtTokenUtil.refreshToken(oldToken);
		}else {
			return null;
		}
		
	}
	
}
