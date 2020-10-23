package com.internshala.customValidator;



import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = UniqueRegValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueReg {

	public String message() default "This hospital id is already registered!";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}

