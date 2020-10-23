package com.internshala.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class AvailableBloodSample {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE3")	
//	@SequenceGenerator(name="SEQUENCE2", sequenceName="RECV_REGISTRATION", allocationSize=1001)
	private int id;
	private String hospRegId;
	private String bloodGrpName;
	private int qty;
//	private int bloodGrpId;

}
