package br.com.desafio.meetup.conf;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class PropertiesConfigurationTest {
	
	@Bean
	@Profile("test")
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true"); // permite ver o sql que e gerado pelo hibernate
	    properties.setProperty("hibernate.hbm2ddl.auto", "create"); //para o hibernate gerenciar o banco
		return properties;
	}

}
