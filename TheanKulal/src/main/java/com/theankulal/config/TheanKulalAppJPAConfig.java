package com.theankulal.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages ={
		"com.theankulal.repository"}, entityManagerFactoryRef = "theanKulalEntityManagerFactory", transactionManagerRef = "theankulalTransactionManager")
@EnableJpaAuditing
public class TheanKulalAppJPAConfig {
	
	@Value("${spring.datasource.driver.class}")
	private String driverClass;
	
	@Value("${spring.macapp.datasource.url}")
	private String dataSourceUrl;
	
	@Value("${spring.macapp.datasource.username}")
	private String dataSourceUserName;
	
	@Value("${spring.macapp.datasource.password}")
	private String dataSourcePassword;
	
	@Value("${spring.jpa.show-sql}")
	private String jpaShowSql;
	
	@Value("${spring.jpa.format_sql}")
	private String jpaFormatSql;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String jpaHibernateDialect;
	
	@Value("${spring.jpa.db.base.package}")
	private String basePackage;
	
	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String defaultSchema;
	
	@Bean
	@Primary
	public DataSource dataSource(){
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClass);
		hikariConfig.setJdbcUrl(dataSourceUrl);
		hikariConfig.setUsername(dataSourceUserName);
		hikariConfig.setPassword(dataSourcePassword);
		hikariConfig.setMaximumPoolSize(2);
		return new HikariDataSource(hikariConfig);
	}
	
	@Bean
	@Primary
	public HibernateExceptionTranslator hibernateExceptionTranslator(){
		return new HibernateExceptionTranslator();
	}
	
	@Bean
	public EntityManagerFactory theanKulalEntityManagerFactory(){
		HibernateJpaVendorAdapter hibernateVendor = new HibernateJpaVendorAdapter();
		hibernateVendor.setShowSql((jpaShowSql == null ? false : Boolean.valueOf(jpaShowSql)));
		hibernateVendor.setDatabase(Database.POSTGRESQL);
		hibernateVendor.setDatabasePlatform(jpaHibernateDialect);
		
		LocalContainerEntityManagerFactoryBean beanFactory = new LocalContainerEntityManagerFactoryBean();
		beanFactory.setJpaVendorAdapter(hibernateVendor);
		beanFactory.setPackagesToScan(basePackage);
		beanFactory.setDataSource(dataSource());
		Properties properties = new Properties();
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		properties.setProperty("hibernate.default_schema", defaultSchema);
		properties.setProperty("hibernate.hbm2ddl.auto", "none");

		// Batch Insert
		properties.setProperty("hibernate.generate_statistics", "false");
		properties.setProperty("hibernate.jdbc.batch_size", "20");
		properties.setProperty("hibernate.order_inserts", "true");

		beanFactory.setJpaProperties(properties);
		beanFactory.afterPropertiesSet();
		
		return beanFactory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager theankulalTransactionManager(){
		JpaTransactionManager jpaManager = new JpaTransactionManager();
		JpaDialect jpaDialect = new HibernateJpaDialect();
		jpaManager.setEntityManagerFactory(theanKulalEntityManagerFactory());
		jpaManager.setJpaDialect(jpaDialect);
		return jpaManager;
	}
	
	@Bean
	public JdbcTemplate macappJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
	
	@Bean
	public NamedParameterJdbcTemplate nameParamterJdbcTemplate(){
		return new NamedParameterJdbcTemplate(dataSource());
	}
	
	@Bean
	public AuditorAware<String> auditorAware(){
		return null;
	}
	
//	@Bean(name="macAppEntityManagerFactory")
//	public LocalSessionFactoryBean sessionFactory() {
//	    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//	    return sessionFactory;
//	} 

}
