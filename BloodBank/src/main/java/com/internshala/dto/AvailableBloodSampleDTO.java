package com.internshala.dto;

import java.io.Serializable;


import lombok.Data;

@Data
public class AvailableBloodSampleDTO implements Serializable{
	private int id;
	private String hospRegId;
	private String bloodGrpName;
	private int qty;
	private int bloodGrpId;

}
