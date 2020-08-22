package br.com.desafio.meetup.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.desafio.meetup.conf.AppWebConfiguration;
import br.com.desafio.meetup.conf.DataSourceConfigurationTest;
import br.com.desafio.meetup.conf.JPAConfiguration;
import br.com.desafio.meetup.daos.ComunidadeDAO;
import br.com.desafio.meetup.daos.EventoDAO;
import br.com.desafio.meetup.models.Comunidade;
import br.com.desafio.meetup.models.Evento;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, 
		DataSourceConfigurationTest.class, ComunidadeDAO.class})
@ActiveProfiles("test")
public class ComunidadeControllerTest {
	
	@Autowired
	private ComunidadeDAO comunidadeDao;
	 
	 @Autowired
	private EventoDAO eventoDao;
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Before
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	@Transactional
	public void deveRetornarParaHomeComOsLivros() throws Exception{
		
		Comunidade comunidade = new Comunidade();
		comunidade.setId(1);
		comunidade.setCidade("Teste");
		comunidade.setNome("TesteNome");
		comunidade.setLogo("logo");
		comunidadeDao.gravar(comunidade);
		
		comunidade = new Comunidade();
		comunidade.setId(2);
		comunidade.setCidade("Teste");
		comunidade.setNome("TesteNome");
		comunidade.setLogo("logo");
		comunidadeDao.gravar(comunidade);
		
		Evento evento = new Evento();
		evento.setComunidadeId(1);
		eventoDao.gravar(evento);
		
		evento = new Evento();
		evento.setComunidadeId(1);
		eventoDao.gravar(evento);
		
		evento = new Evento();
		evento.setComunidadeId(2);
		eventoDao.gravar(evento);
		
	    mockMvc.perform(MockMvcRequestBuilders.get("/listarEventos"))
	            .andExpect(MockMvcResultMatchers.model().attributeExists("comunidade"))
	            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/comunidade/listaEvento.jsp"));
	}

}
