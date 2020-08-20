package br.com.desafio.meetup.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.desafio.meetup.daos.EventoDAO;
import br.com.desafio.meetup.models.Comunidade;
import br.com.desafio.meetup.models.Evento;

@Controller
@RequestMapping("eventos")
public class EventoController {
	@Autowired	
	private EventoDAO eventoDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Evento> eventos = eventoDao.listar();
		ModelAndView modelAndView = new ModelAndView("evento/lista");
		modelAndView.addObject("eventos",eventos);
		
		return modelAndView;
	}
	
	public void grava(Evento evento) {
		eventoDao.gravar(evento);
	}
	
}
