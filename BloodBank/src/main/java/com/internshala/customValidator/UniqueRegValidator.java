package com.internshala.customValidator;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.internshala.entity.HospitalRegistration;
import com.internshala.service.BloodBankService;

public class UniqueRegValidator implements ConstraintValidator<UniqueReg, String> {

	 	@Autowired
	    private BloodBankService userService;

	    public void initialize(UniqueEmail unique) {
	        unique.message();
	    }
	    @Override
	    public boolean isValid(String hospRegId, ConstraintValidatorContext context) {
	        if (userService != null &&  userService.existsByRegId(hospRegId,HospitalRegistration.class) ) {
	            return false;
	        }
	        return true;
	    }
	
}