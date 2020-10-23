package com.internshala.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.internshala.customValidator.UniqueReg;

import lombok.Data;

@Table
@Entity
@Data
public class HospitalRegistration {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")	
//	@SequenceGenerator(name="SEQUENCE1", sequenceName="HOSP_REGISTRATION", allocationSize=1001)
	private int hospId;
	private String hospName;
	private String hospAddrs;
	private String hospCity;
	private Long hospMobNO;
	@UniqueReg
	private String hospRegId;
	private String hospPass;
	private String hospCPass;
	@Transient
	private boolean jsFlag=false;
	
}
