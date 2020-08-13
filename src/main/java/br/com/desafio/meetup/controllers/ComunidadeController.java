package br.com.desafio.meetup.controllers;

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
import br.com.desafio.meetup.validation.ComunidadeValidation;

@Controller
@RequestMapping("comunidades")
public class ComunidadeController {
	
	@Autowired	
	private ComunidadeDAO comunidadeDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ComunidadeValidation());
		
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form() {
		return new ModelAndView("/comunidade/form");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView grava(@Valid Comunidade comunidade, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return form();
		}
		comunidadeDao.gravar(comunidade);
		redirectAttributes.addFlashAttribute("sucesso", "Comunidade Cadastrada com sucesso");
		
		return new ModelAndView("redirect:eventos");
	}

}
