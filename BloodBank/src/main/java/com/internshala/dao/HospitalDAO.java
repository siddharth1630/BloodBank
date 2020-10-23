package com.internshala.dao;

import java.util.List;

import com.internshala.bo.AvailableBloodSampleBO;
import com.internshala.bo.BloodRequestBO;
import com.internshala.bo.HospitalBO;
import com.internshala.entity.HospitalRegistration;

public interface HospitalDAO {
	public int insertHospital(HospitalBO bo);
	public HospitalBO gethospitalDetails(String hospRegId);
	
	public int insertBlood(AvailableBloodSampleBO bo);
	public int updateBlood(AvailableBloodSampleBO bo);
	
	public List<BloodRequestBO> getListOfBloodReq(String hospRegId);
	public boolean existsByRegId(String regId, Class<HospitalRegistration> class1);
	
	public List<HospitalBO> fetchAllHospitals();
}
