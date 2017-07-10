package com.cart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cart.model.BaseDomain;
import com.cart.model.Blog;
import com.cart.model.BlogComment;
import com.cart.model.Friend;
import com.cart.model.Job;
import com.cart.model.JobApplication;
import com.cart.model.UploadFile;
import com.cart.model.User;

@Configuration
@EnableTransactionManagement
public class DBConfig {
	@Autowired
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.use_sql_commnets", "true");
		lsf.addProperties(hibernateProperties);
		return lsf.addAnnotatedClass(User.class).addAnnotatedClass(BaseDomain.class).addAnnotatedClass(Blog.class)
				.addAnnotatedClass(Job.class).addAnnotatedClass(Friend.class).addAnnotatedClass(BlogComment.class)
				.addAnnotatedClass(JobApplication.class).addAnnotatedClass(UploadFile.class).buildSessionFactory();

	}

	@Bean(name="dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("system");
		dataSource.setPassword("system");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransManager() {
		return new HibernateTransactionManager(sessionFactory());
	}
}
