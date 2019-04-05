package com.libraryuser.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * Application Configuration Class
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.libraryuser")
public class AppConfiguration {
	
	/*
	 * Method to create and return datasource
	 */
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/library_users");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
	}
	
	/*
	 * Method to define transaction manager
	 */
	@Bean(name="transactionManager")
	public DataSourceTransactionManager getTransactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		
		DataSource dataSource = this.getDataSource();
		txManager.setDataSource(dataSource);
		
		return txManager;
	}
}
