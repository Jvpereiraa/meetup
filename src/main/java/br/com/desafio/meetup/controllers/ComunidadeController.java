package br.com.desafio.meetup.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.desafio.meetup.daos.ComunidadeDAO;
import br.com.desafio.meetup.models.Comunidade;
import br.com.desafio.meetup.models.ComunidadeJson;
import br.com.desafio.meetup.models.Evento;
import br.com.desafio.meetup.validation.ComunidadeValidation;

@Controller
@RequestMapping("/comunidade")
public class ComunidadeController {
	
	@Autowired	
	private ComunidadeDAO comunidadeDao;
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ComunidadeValidation());
		
	}*/
	
	/*@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(Comunidade comunidade) {
		return new ModelAndView("/comunidade/form");
	}*/
	
	/*@RequestMapping(method = RequestMethod.POST)
	public ModelAndView grava(Comunidade comunidade,
			RedirectAttributes redirectAttributes) {
		comunidadeDao.gravar(comunidade);
		redirectAttributes.addFlashAttribute("sucesso", "Comunidade Cadastrada com sucesso");
		
		return new ModelAndView("redirect:eventos");
	}*/
	
	@RequestMapping(value = "/listarEventos", method = RequestMethod.GET)
	public ModelAndView listarEventos(Comunidade comunidade, RedirectAttributes redirectAttributes) {
		comunidade = comunidadeDao.getComunidadeById(comunidade.getId());
		if(comunidade.getNome() == null || comunidade.getNome().isEmpty()) {
			redirectAttributes.addFlashAttribute("falha", "Nenhuma comunidade com esse ID");
			redirectAttributes.addFlashAttribute("show", "mostra-div");
			return new ModelAndView("redirect:/");
		}
		ComunidadeJson comunidadeJson = new ComunidadeJson();
		comunidadeJson = comunidade.toComunidadeJson(comunidade);
		ModelAndView modelAndView = new ModelAndView("/comunidade/listaEvento");
		modelAndView.addObject("comunidade", comunidadeJson);
		return modelAndView;
	}
	
	@RequestMapping(value = "/listarComunidadeCidade", method = RequestMethod.GET)
	public ModelAndView listarComunidadeCidade(Comunidade comunidade, RedirectAttributes redirectAttributes) {
		List<Comunidade> comunidades = comunidadeDao.getComunidadeByCidade(comunidade.getCidade());
		if(comunidades == null || comunidades.isEmpty()) {
			redirectAttributes.addFlashAttribute("falha", "Nenhuma comunidade nessa regiao");
			redirectAttributes.addFlashAttribute("show", "mostra-div");
			return new ModelAndView("redirect:/");
		}
		List<ComunidadeJson> comunidadeJson = new ArrayList<>();
		for(Comunidade comunidadeTemp : comunidades) {
			comunidadeJson.add(comunidadeTemp.toComunidadeJsonProximoEvento(comunidadeTemp));
		}
		ModelAndView modelAndView = new ModelAndView("/comunidade/listarComunidadeCidade");
		modelAndView.addObject("comunidades", comunidadeJson);
		return modelAndView;
	}

}
