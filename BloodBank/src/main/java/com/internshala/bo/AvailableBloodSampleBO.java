package com.internshala.bo;

import lombok.Data;

@Data
public class AvailableBloodSampleBO {
	private int id;
	private String hospRegId;
	private String bloodGrpName;
	private int qty;
	private int bloodGrpId;

}
