package br.com.desafio.meetup.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.desafio.meetup.daos.ComunidadeDAO;
import br.com.desafio.meetup.daos.EventoDAO;
import br.com.desafio.meetup.models.Comunidade;
import br.com.desafio.meetup.models.ComunidadeJson;
import br.com.desafio.meetup.models.Comunidades;
import br.com.desafio.meetup.models.Evento;
import br.com.desafio.meetup.utils.DadosIniciais;
import br.com.desafio.meetup.validation.ComunidadeValidation;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired	
	private ComunidadeDAO comunidadeDao;
	
	@Autowired	
	private EventoDAO eventoDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ComunidadeValidation());
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar(Comunidade comunidadeParm) {
		List<Comunidade> listaComunidades = comunidadeDao.listar();
		if(listaComunidades.isEmpty()) {
			String json = new DadosIniciais().getJson();
			if(!json.isEmpty()) {
				Comunidades comunidades = new Gson().fromJson(json, Comunidades.class);
				List<ComunidadeJson> listaComunidade = comunidades.getComunidade();
				if(listaComunidade != null)
					listaComunidade.forEach(comunidade -> {
						comunidadeDao.adicionaRegistrosIniciais(comunidade);
					});
			}
			listaComunidades = comunidadeDao.listar();
		}
		List<ComunidadeJson> listaComunidadesJson = new ArrayList<>();
		for(Comunidade comunidadeTemp : listaComunidades) {
			ComunidadeJson comunidadeJson = new ComunidadeJson();
			comunidadeJson = comunidadeTemp.toComunidadeJsonProximoEvento(comunidadeTemp);
			if(comunidadeJson.getEvento() != null && !comunidadeJson.getEvento().isEmpty())
				listaComunidadesJson.add(comunidadeJson);
		}
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("comunidades",listaComunidadesJson);
		
		System.out.println("Entrando na home do meeetup");
		return modelAndView;
	}


}

