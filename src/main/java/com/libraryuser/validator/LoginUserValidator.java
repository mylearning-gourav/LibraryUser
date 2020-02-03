package com.libraryuser.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.libraryuser.model.User;

@Component
public class LoginUserValidator implements Validator {

	public boolean supports(Class cls) {
		return User.class.isAssignableFrom(cls);
	}

	public void validate(Object object, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "error.email", "Email is requird");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "error.password", "Password is requird");
	}

}
