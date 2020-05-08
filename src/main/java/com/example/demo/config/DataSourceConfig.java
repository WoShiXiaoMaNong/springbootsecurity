package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.two")
	DataSource dsTest() {
		 return DruidDataSourceBuilder.create().build();
	}
	
	
	@Bean
    public JdbcTemplate testJdbcTemplate ( @Qualifier("dsTest")  DataSource testDataSource ) {
        return new JdbcTemplate(testDataSource);
    }
	
}
