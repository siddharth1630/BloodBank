package com.internshala.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.internshala.customValidator.UniqueEmail;

import lombok.Data;

@Table
@Entity
@Data
public class ReciverRegistration {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="SEQUENCE2")	
//	@SequenceGenerator(name="SEQUENCE2", sequenceName="RECV_REGISTRATION", allocationSize=5001)
	private int recvId;
	private String recvName;
	private String recvAddrs;
	private String recvCity="PUNE";
	private Long recvMobNO;
	private String bloodGrpName="B+";
	@UniqueEmail
	private String recvEmail;
	private String recvPass;
	private String recvCPass;
	@Transient
	private boolean jsFlag=false;
	
}
