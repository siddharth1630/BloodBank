package com.internshala.dto;


import java.io.Serializable;

import lombok.Data;
@Data
public class HospitalDTO implements Serializable {
	
	private int hospId;
	private String hospName;
	private String hospAddrs;
	private String hospCity;
	private Long hospMobNO;
	private String hospRegId;
	private String hospPass;
	private String hospCPass;
	
}
