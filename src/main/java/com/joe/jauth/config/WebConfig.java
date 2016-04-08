package com.joe.jauth.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan("com.joe.jauth")
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig {

	@Bean
	public DataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
	    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/oauthdb"); 
	    hikariConfig.setUsername("root");
	    hikariConfig.setPassword("");
	    
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}