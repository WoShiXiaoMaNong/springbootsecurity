package com.example.demo.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface MyUserDetailsServiceMapper {
	
	
	@Select("select name userName,password,enabled\n" +
			"from sys_user u\n"
			+ "where u.name = #{userName}")
	public MyUserDetails findByUserName(@Param("userName") String userName);
	
	@Select("select role_code \n" +
			"from sys_user su,sys_user_role sur ,sys_role sr\n"
			+ "where su.id = sur.user_id \n"
			+ "and sur.role_id = sr.id  \n"
			+ "and su.name = #{userName}")
	public List<String> findRolesByUserName(@Param("userName") String userName);

	
	@Select("<script>"
			+ "select sm.url from sys_menu sm ,sys_role sr ,sys_role_menu srm \n" 
			+ "where sm.id = srm.menu_id  \n"
			+ "and srm.role_id = sr.id  \n"
			+ "and sr.role_code in \n"
			+ "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>"
			+ "#{roleCode}"
			+ "</foreach>"
			+ "</script>")
	public List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

}
