package br.com.desafio.meetup.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.desafio.meetup.controllers.ComunidadeController;
import br.com.desafio.meetup.controllers.HomeController;
import br.com.desafio.meetup.daos.ComunidadeDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class,ComunidadeController.class,ComunidadeDAO.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public MessageSource messageSourcer() {
		ReloadableResourceBundleMessageSource messageSourcer = 
				new ReloadableResourceBundleMessageSource();
		messageSourcer.setBasename("/WEB-INF/messages");
		messageSourcer.setDefaultEncoding("UTF-8");
		messageSourcer.setCacheSeconds(1);
		
		return messageSourcer;
	}

}
