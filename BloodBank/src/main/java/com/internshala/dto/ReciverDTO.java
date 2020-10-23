package com.internshala.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class ReciverDTO implements Serializable {
	private int recvId;
	private String recvName;
	private String recvAddrs;
	private String recvCity;
	private Long recvMobNO;
	private String bloodGrpName;
	private String recvEmail;
	private String recvPass;
	private String recvCPass;
}
