package com.example.demo.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	MyUserDetailsServiceMapper myUserDetailsServiceMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		MyUserDetails myUserDetails = this.myUserDetailsServiceMapper.findByUserName(username);
		
		//加载该用户下的所有角色（role）
		List<String> roleCodes = this.myUserDetailsServiceMapper.findRolesByUserName(username);
		
		//加载该用户下所有有权限访问的url
		List<String> authorties = this.myUserDetailsServiceMapper.findAuthorityByRoleCodes(roleCodes);
		
		//角色是一个的权限，需要有ROLE_作为前缀
		roleCodes = roleCodes.stream()
				.map(rc -> "ROLE_" + rc)
				.collect(Collectors.toList());
		
		authorties.addAll(roleCodes);
		
		myUserDetails.setAuthorities(
				AuthorityUtils.commaSeparatedStringToAuthorityList(
						String.join(",", authorties)
						)
				
				);
		
		return myUserDetails;
	}

}
