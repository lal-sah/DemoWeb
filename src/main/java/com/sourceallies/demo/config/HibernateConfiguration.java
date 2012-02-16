/**
 * 
 */
package com.sourceallies.demo.config;

import org.springframework.context.annotation.Configuration;

/**
 * Annontation based Hibernate configurations.
 * 
 * @author lsah
 * 
 */
@Configuration
public class HibernateConfiguration {

	// @Value(value = "#{dataSource}")
	// private DataSource dataSource;
	//
	// @Bean
	// public AnnotationSessionFactoryBean sessionFactory() {
	// Properties properties = new Properties();
	// properties.put("hibernate.dialect", HSQLDialect.class.getName());
	// properties.put("hibernate.format_sql", "true");
	// properties.put("hibernate.show_sql", "true");
	// properties.put("hibernate.hbm2ddl.auto", "create-drop");
	//
	// AnnotationSessionFactoryBean sessionFactoryBean = new
	// AnnotationSessionFactoryBean();
	// sessionFactoryBean.setAnnotatedClasses(new Class[] {});
	// sessionFactoryBean.setHibernateProperties(properties);
	// sessionFactoryBean.setDataSource(dataSource);
	// sessionFactoryBean.setSchemaUpdate(Boolean.TRUE);
	// System.out.println("session  factory: " + sessionFactoryBean);
	// return sessionFactoryBean;
	// }
	//
	// @Bean
	// public HibernateTransactionManager transactionManager() {
	// System.out.println("building transaction manager...");
	// return new HibernateTransactionManager(sessionFactory().getObject());
	// }

}
