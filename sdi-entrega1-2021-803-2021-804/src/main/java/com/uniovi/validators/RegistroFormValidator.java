package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UserService;

@Component
public class RegistroFormValidator implements Validator{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "Error.empty");
		if (user.getEmail().length() < 5 || user.getEmail().length() > 24) {
			errors.rejectValue("dni", "Error.signup.email.length");
		}
		if (userService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("dni", "Error.signup.email.duplicate");
		}
		if (user.getNombre().length() < 5 || user.getNombre().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		if (user.getApellidos().length() < 5 || user.getApellidos().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}
		if (user.getContraseña().length() < 5 || user.getContraseña().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (!user.getContraseñaRepetida().equals(user.getContraseña())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
		}
	}

}
