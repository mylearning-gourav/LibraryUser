package com.libraryuser.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.libraryuser.model.User;

@Component
public class UpdateRequestValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return User.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object target, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userId", "error.userId", "userId is requird");
	}

}
