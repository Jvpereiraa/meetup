package br.com.desafio.meetup.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //Habilitar a transacao
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendoAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendoAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:mysql://localhost:3306/meetup?useTimezone=true&serverTimezone=UTC");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		factoryBean.setDataSource(dataSource);
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true"); // permite ver o sql que e gerado pelo hibernate
	    properties.setProperty("hibernate.hbm2ddl.auto", "update"); //para o hibernate gerenciar o banco
	    
	    factoryBean.setJpaProperties(properties);
	    
	    factoryBean.setPackagesToScan("br.com.desafio.meetup.models"); //escanear para criar as entidades no banco
	    
	    return factoryBean;
	}
	
	//Metodo que vai criar/cuidar das transicoes
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
