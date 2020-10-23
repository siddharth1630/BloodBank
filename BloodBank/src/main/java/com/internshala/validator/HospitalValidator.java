package com.internshala.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.internshala.entity.HospitalRegistration;
import com.internshala.entity.ReciverRegistration;

@Component
public class HospitalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(HospitalRegistration.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		HospitalRegistration hosp=null;
		// chg commnad type to hospital type
		hosp=(HospitalRegistration) target;
		if(hosp.getHospName()==null || hosp.getHospName().isEmpty()) {
			errors.rejectValue("hospName", "hosp.name.required"); 
		}
		else if(hosp.getHospName().length()<3 || hosp.getHospName().length()>15) {
			errors.rejectValue("hospName","hosp.name.length");
		}
		
		if(hosp.getHospAddrs()==null || hosp.getHospAddrs().isEmpty()) {
			errors.rejectValue("hospAddrs", "hosp.addrs.required"); 
		}
		else if(hosp.getHospAddrs().length()<4 || hosp.getHospAddrs().length()>15) {
			errors.rejectValue("hospAddrs","hosp.addrs.length");
		}
		
		if(hosp.getHospMobNO()==null) {
			errors.rejectValue("hospMobNO","hosp.mb.required");
		}
		
		if(hosp.getHospRegId()==null || hosp.getHospRegId().isEmpty()) {
			errors.rejectValue("hospRegId", "hosp.regId.required"); 
		}
		
		if(hosp.getHospPass()==null && hosp.getHospPass().isEmpty()) {
			errors.rejectValue("hospPass", "hosp.pass.required"); 
		}
		else if(hosp.getHospPass().length()<4 || hosp.getHospPass().length()>15) {
			errors.rejectValue("hospPass","hosp.pass.length");
		}
		if(hosp.getHospCPass()==null && hosp.getHospCPass().isEmpty()) {
			errors.rejectValue("hospCPass", "hosp.cpass.required"); 
		}
		else if(hosp.getHospCPass().length()<4 || hosp.getHospCPass().length()>15) {
			errors.rejectValue("hospCPass","hosp.cpass.length");
		}
		else if(hosp.getHospCPass().equals(hosp.getHospPass())) {
			
		}
		else {
			errors.rejectValue("hospCPass", "hosp.cpass.cnfrm");
		}
		

	}

}
