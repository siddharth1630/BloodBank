package com.internshala.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class BloodRequestDTO implements Serializable{
	
	private int id;
	private String recvEmail;
	private String hospRegId;
	private int qty;
	private String bloodGrpName;

}
