package com.internshala.model;

import lombok.Data;

@Data
public class Login {
//	private String HospRegId;
	private String email;
	private String pass;
	private boolean jsFlag=false;
}
