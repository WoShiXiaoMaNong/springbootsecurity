package com.example.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.auth.MyUserDetailsServiceMapper;

@SpringBootTest
class SpringSecurityDemoApplicationTests {
	
	
	@Resource
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	MyUserDetailsServiceMapper mmDetailsServiceMapper;
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

}
