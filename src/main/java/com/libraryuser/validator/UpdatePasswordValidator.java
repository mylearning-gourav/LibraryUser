package com.libraryuser.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.libraryuser.model.User;

@Component
public class UpdatePasswordValidator implements Validator {

	public boolean supports(Class cls) {
		return User.class.isAssignableFrom(cls);
	}

	public void validate(Object object, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userId", "error.userId", "userId is requird");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "error.password", "Password is requird");
		User user = (User) object;
		
		if(user.getUserId() <= 0)
			error.rejectValue("userId", "negativeValue", new Object[]{"'userId'"}, "userId can't be negative or zero");
	}

}
