package com.example.demo.auth;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component("rbacService")
public class MyRBACService {
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	/**
	 * 判断用户对一个请求资源的访问权限
	 * @param req
	 * @param auth
	 * @return
	 */
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		String requestUri = request.getRequestURI();
		if(principal instanceof UserDetails) {
			Collection<? extends GrantedAuthority> authsAuthorities =((UserDetails)principal).getAuthorities();
			return authsAuthorities.stream()
								.anyMatch(url -> 
												antPathMatcher.match(url.getAuthority(),requestUri)
										 );

		}
		return false;
	}

}
