package com.example.demo.auth.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {
	
	
	private String secret;
	private Long expriation;
	private String header;
	
	
	/**
	 * 生成Token
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>(2);
		claims.put("sub",userDetails.getUsername());
		claims.put("created",new Date());
		return generateToken(claims);
	}
	
	
	/**
	 * 根据Token获取用户名
	 * @param token
	 * @return
	 */
	public String getUserNameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}catch (Exception e) {
			username = null;
		}
		
		return username;
	}

	
	/**
	 * 刷新Token
	 * @param token
	 * @return
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = this.getClaimsFromToken(token);
			claims.put("created",new Date());
			refreshedToken = this.generateToken(claims);
		} catch(Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	/**
	 * 验证Token的合法性
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = this.getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername())&& !this.isTokenExpired(token) );
	}
	
	
	/**
	 * 判断Token是否过期
	 * @param token
	 * @return
	 */
	public boolean isTokenExpired(String token) {
		try {
			Claims claims = this.getClaimsFromToken(token);
			Date expiratDate = claims.getExpiration();
			return expiratDate.before(new Date());
		} catch (Exception e) {
			return false;
		}
	
	}


	/**
	 * 
	 * @param token
	 * @return
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(this.secret)
							.parseClaimsJws(token)
							.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * 根据 claims生成Token
	 * @param claims
	 * @return
	 */
	private String generateToken(Map<String, Object> claims) {
		Date expirationDate = new Date(System.currentTimeMillis() + this.expriation);
		
		return Jwts.builder().setClaims(claims)
					.setExpiration(expirationDate)
					.signWith(SignatureAlgorithm.HS512, this.secret)
					.compact();
	}


	public String getSecret() {
		return secret;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public Long getExpriation() {
		return expriation;
	}


	public void setExpriation(Long expriation) {
		this.expriation = expriation;
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}
	
	
	
}
