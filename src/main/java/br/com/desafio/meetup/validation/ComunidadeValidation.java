package br.com.desafio.meetup.validation;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.desafio.meetup.models.Comunidade;

public class ComunidadeValidation implements Validator {
	
	public void valida(Comunidade comunidade, Errors erros) {
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Comunidade.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "linguagem", "field.required");
	}

}
