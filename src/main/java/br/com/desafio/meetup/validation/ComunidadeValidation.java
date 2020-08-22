package br.com.desafio.meetup.validation;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.desafio.meetup.models.Comunidade;

public class ComunidadeValidation implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return Comunidade.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "id", "field.required");
	}

}
