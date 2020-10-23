package com.internshala.customValidator;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.internshala.entity.ReciverRegistration;
import com.internshala.service.BloodBankService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	 	@Autowired
	    private BloodBankService userService;

	    public void initialize(UniqueEmail unique) {
	        unique.message();
	    }
	    @Override
	    public boolean isValid(String email, ConstraintValidatorContext context) {
	        if (userService != null &&  userService.existsByEmail(email,ReciverRegistration.class) ) {
	            return false;
	        }
	        return true;
	    }
	
}