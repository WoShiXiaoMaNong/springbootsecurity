package com.example.demo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.auth.MyFailureAuthHandler;
import com.example.demo.auth.MySuccessAuthHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Resource
	private MySuccessAuthHandler mySuccessAuthHandler;
	@Resource
	private MyFailureAuthHandler myFauilerAuthHandler;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.formLogin()
				.loginPage("/login.html")
				.usernameParameter("uname")
				.passwordParameter("pwd")
				.loginProcessingUrl("/login")
				//.defaultSuccessUrl("/index")
				.successHandler(mySuccessAuthHandler)
				.failureHandler(myFauilerAuthHandler)
			.and()
			.authorizeRequests()
				.antMatchers("/login.html","login").permitAll()
				.antMatchers("/biz1","/biz2","/json")
					.hasAnyAuthority("ROLE_user","ROLE_admin")
				.antMatchers("/syslog","/sysuser")
					.hasAnyAuthority("ROLE_admin")
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.invalidSessionUrl("/login")
					.sessionFixation().migrateSession();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user")
				.password(passwordEncoder().encode("123"))
				.roles("user")
					.and()
				.withUser("admin")
				.password(passwordEncoder().encode("123"))
				.roles("admin")
				.and()
				.passwordEncoder(passwordEncoder());
	}
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
