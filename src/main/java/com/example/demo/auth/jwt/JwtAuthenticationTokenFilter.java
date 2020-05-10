package com.example.demo.auth.jwt;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.auth.MyUserDetailsService;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

	@Resource 
	private JwtTokenUtil jwtTokenUtil;
	
	@Resource
	private MyUserDetailsService myUserDetailsService;
	
	
	//在SecurityConfig.java里设定fileter顺序 http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
		if(!StringUtils.isEmpty(jwtToken)) {
			
			//getUserNameFromToken 方法会对token做一次合法性校验
			String username = jwtTokenUtil.getUserNameFromToken(jwtToken);
			
			if(username != null && 
					SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
				
				if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					//token没有过期，用户名也正确,通过验证
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		}else {
			 String originRequestUrl = request.getScheme()
		                + "://" + request.getServerName()
		                + ":" + request.getServerPort()
		                + request.getContextPath()
		                + request.getRequestURI();
	
			response.sendRedirect("http://www.baidu.com?target=" + originRequestUrl);
		}
		
		
	}

}
