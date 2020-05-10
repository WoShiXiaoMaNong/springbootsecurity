package com.example.demo;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import com.example.demo.auth.MyUserDetailsService;
import com.example.demo.auth.MyUserDetailsServiceMapper;

@SpringBootTest
class SpringSecurityDemoApplicationTests {
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Resource
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	MyUserDetailsServiceMapper mmDetailsServiceMapper;
	@Resource
	MyUserDetailsService s;
	
	@Test
	void contextLoads() {
		List<String> s = mmDetailsServiceMapper.findRolesByUserName("user1");
		String p = bCryptPasswordEncoder.encode("123");
		System.out.println(p);
		List<String> urls = mmDetailsServiceMapper.findAuthorityByRoleCodes(s);
		for(String a : urls) {
			System.out.println(a);
		} 
		
		
	}

	
	@Test
	void test() {
		UserDetails md = s.loadUserByUsername("admin1");
		Collection<? extends GrantedAuthority> authsAuthorities =md.getAuthorities();
		for(GrantedAuthority authority : authsAuthorities) {
			String auth = authority.getAuthority();
			System.out.println(auth);
		}
		boolean result = authsAuthorities.stream().anyMatch(url -> antPathMatcher.match("/hello", url.getAuthority()));
		
		System.out.println(result);
	}
}
