package com.internshala.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.internshala.entity.ReciverRegistration;

@Component
public class ReciverValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ReciverRegistration.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ReciverRegistration recv=null;
		// chg commnad type to recvloyee type
		recv=(ReciverRegistration) target;
		if(recv.getRecvName()==null || recv.getRecvName().isEmpty()) {
			errors.rejectValue("recvName", "recv.name.required"); 
		}
		else if(recv.getRecvName().length()<3 || recv.getRecvName().length()>15) {
			errors.rejectValue("recvName","recv.name.length");
		}
		
		if(recv.getRecvAddrs()==null || recv.getRecvAddrs().isEmpty()) {
			errors.rejectValue("recvAddrs", "recv.addrs.required"); 
		}
		else if(recv.getRecvAddrs().length()<4 || recv.getRecvAddrs().length()>15) {
			errors.rejectValue("recvAddrs","recv.addrs.length");
		}
		
		if(recv.getRecvMobNO()==null) {
			errors.rejectValue("recvMobNO","recv.mb.required");
		}
		
		if(recv.getRecvEmail()==null || recv.getRecvEmail().isEmpty()) {
			errors.rejectValue("recvEmail", "recv.email.required"); 
		}
		else if (recv.getRecvEmail().endsWith(".com")) {
			
		}
		else {
			errors.rejectValue("recvEmail", "recv.email.present");
		}
		if(recv.getRecvPass()==null || recv.getRecvPass().isEmpty()) {
			errors.rejectValue("recvPass", "recv.pass.required"); 
		}
		else if(recv.getRecvPass().length()<4 || recv.getRecvPass().length()>15) {
			errors.rejectValue("recvPass","recv.pass.length");
		}
		if(recv.getRecvCPass()==null || recv.getRecvCPass().isEmpty()) {
			errors.rejectValue("recvCPass", "recv.cpass.required"); 
		}
		else if(recv.getRecvCPass().length()<4 || recv.getRecvCPass().length()>15) {
			errors.rejectValue("recvCPass","recv.cpass.length");
		}
		else if(recv.getRecvCPass().equals(recv.getRecvPass())) {
			
		}
		else {
			errors.rejectValue("recvCPass", "recv.cpass.cnfrm");
		}
		

	}

}
