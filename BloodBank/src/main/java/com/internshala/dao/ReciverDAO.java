package com.internshala.dao;

import java.util.List;

import com.internshala.bo.AvailableBloodSampleBO;
import com.internshala.bo.BloodRequestBO;
import com.internshala.bo.ReciverBO;
import com.internshala.entity.ReciverRegistration;

public interface ReciverDAO {

	public int insertReciver(ReciverBO bo);
	public ReciverBO getReciverDetails(String recvEmail);
	
	public List<AvailableBloodSampleBO> getAllBloodSamples();
	public int insertBloodReq(BloodRequestBO bo,boolean flag);
	public boolean existsByEmail(String email, Class<ReciverRegistration> class1);

}
