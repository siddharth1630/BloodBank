package com.internshala.bo;


import lombok.Data;

@Data
public class BloodRequestBO {
	
	private int id;
	private String recvEmail;
	private String hospRegId;
	private int qty;
	private String bloodGrpName;

}
