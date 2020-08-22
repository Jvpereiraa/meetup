package br.com.desafio.meetup.daos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import br.com.desafio.meetup.conf.DataSourceConfigurationTest;
import br.com.desafio.meetup.conf.JPAConfiguration;
import br.com.desafio.meetup.conf.PropertiesConfigurationTest;
import br.com.desafio.meetup.models.Comunidade;
import br.com.desafio.meetup.models.ComunidadeJson;
import br.com.desafio.meetup.models.Comunidades;
import br.com.desafio.meetup.models.Evento;
import br.com.desafio.meetup.utils.DadosIniciais;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ComunidadeDAO.class,JPAConfiguration.class,
		DataSourceConfigurationTest.class, PropertiesConfigurationTest.class, EventoDAO.class})
@ActiveProfiles("test")
public class ComunidadeDAOTest {
	
	 @Autowired
	 private ComunidadeDAO comunidadeDao;
	 
	 @Autowired
	 private EventoDAO eventoDao;
	 
	@Test
	@Transactional
	public void pegaComunidadePeloId() {
		Comunidade comunidade = new Comunidade();
		comunidade.setId(1);
		comunidade.setCidade("Teste");
		comunidade.setNome("TesteNome");
		comunidade.setLogo("logo");
		comunidadeDao.gravar(comunidade);
		
		Comunidade comunidadeBanco = comunidadeDao.getComunidadeById(1);
		Assert.assertEquals(comunidade.getId(), comunidadeBanco.getId());
		
	}
	
	@Test
	@Transactional
	public void pegaComunidadePelaCidade() {
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


		comunidade = new Comunidade();
		comunidade.setId(3);
		comunidade.setCidade("Testi");
		comunidade.setNome("TesteNome");
		comunidade.setLogo("logo");
		comunidadeDao.gravar(comunidade);
		
		comunidadeDao.gravar(comunidade);
		
		List<Comunidade> comunidadesBanco = comunidadeDao.getComunidadeByCidade("Teste");
		Assert.assertEquals(2, comunidadesBanco.size());
		
		
		
	}
	
	@Test
	@Transactional
	public void pegaEventosPeloIdComunidade() {
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
		
		List<Evento> eventos = comunidadeDao.getEventosByComunidade(1);
		Assert.assertEquals(2, eventos.size());
		
		
		
	}
	

}
