package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.UserDao;
import com.niit.dao.UserDaoImpl;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.Loginmodel;
import com.niit.model.ProfilePicture;
import com.niit.model.User;


@Configuration
@EnableTransactionManagement
@ComponentScan("com.niit")
public class Databaseconfig {

	
	
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/chaton");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

	private Properties getHibernateProperties(){
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.use_sql_commnets", "true");
		return hibernateProperties;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(dataSource);
		
		lsf.addProperties(getHibernateProperties());
		return lsf.addAnnotatedClass(User.class).addAnnotatedClass(Error.class).addAnnotatedClass(BlogPost.class)
				.addAnnotatedClass(Job.class).addAnnotatedClass(Friend.class).addAnnotatedClass(BlogComment.class)
				.addAnnotatedClass(Loginmodel.class).addAnnotatedClass(ProfilePicture.class).buildSessionFactory();

	}

	
	

	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tm= new HibernateTransactionManager(sessionFactory);
		return tm;
	}
	
	@Bean("userDao")
	@Autowired
	public UserDao getUserDAO()
	{
	     return new UserDaoImpl();
	}
}




/*package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Chat;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.ProfilePicture;
import com.niit.model.User;



@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class Databaseconfig {
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("CHAT");
	dataSource.setPassword("hr");
	    return dataSource;
	}
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
properties.put("hibernate.hbm2ddl.auto", "update");
	    return properties;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	 LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 sessionBuilder.addProperties(getHibernateProperties());
	sessionBuilder.addAnnotatedClasses(User.class,BlogPost.class,ProfilePicture.class,Job.class,BlogComment.class,Friend.class,Chat.class);
	
	
     return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	return transactionManager;
	}
	
	
	
}
*/