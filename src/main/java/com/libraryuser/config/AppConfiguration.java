package com.libraryuser.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * Application Configuration Class
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.libraryuser")
//@PropertySource(value= {"classpath:/properties/application-dev.properties"})
@PropertySource(value= {"classpath:application.properties"})
public class AppConfiguration extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(AppConfiguration.class);
	
	/*JDBC Env Variable*/
	@Value("${jdbc.password}")
	private String jdbcPassword;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.url}")
	private String jdbcURL;
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	
	@Autowired
	private Environment environment;
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		logger.debug("Data Source Created: " + this.jdbcURL);
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(this.jdbcDriver);
	    dataSource.setUrl(this.jdbcURL);
	    dataSource.setUsername(this.jdbcUsername);
	    dataSource.setPassword(this.jdbcPassword);

	    return dataSource;
	}
	
	/*
	 * Method to create and return datasource
	 */
	/*@Bean(name="dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/library_users");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
	}*/
	
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
