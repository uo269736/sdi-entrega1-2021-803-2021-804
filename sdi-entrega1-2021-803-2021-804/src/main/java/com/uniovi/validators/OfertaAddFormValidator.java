package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Oferta;

@Component
public class OfertaAddFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Oferta.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Oferta oferta = (Oferta) target;
		if (oferta.getTitulo().length() < 5 ) {
			errors.rejectValue("titulo", "Error.oferta.titulo.length");
		}
		if (oferta.getDescripcion().length() < 20 ) {
			errors.rejectValue("descripcion", "Error.oferta.descripcion.length");
		}
		if (oferta.getCantidad() < 0) {
			errors.rejectValue("cantidad", "Error.oferta.cantidad.negativa");
		}
		
	}
}
