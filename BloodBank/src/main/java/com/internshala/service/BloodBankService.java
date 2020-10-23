package com.internshala.service;

import java.util.List;

import com.internshala.dto.AvailableBloodSampleDTO;
import com.internshala.dto.BloodRequestDTO;
import com.internshala.dto.HospitalDTO;
import com.internshala.dto.ReciverDTO;
import com.internshala.entity.HospitalRegistration;
import com.internshala.entity.ReciverRegistration;


public interface BloodBankService {
	public String registerReciver(ReciverDTO dto);
	public ReciverDTO fetchReciverDetail(String email);
	
	
	
	public String registerHospitol(HospitalDTO dto);
	public HospitalDTO fetchHospitaDetail(String hospRegId);
	
	public String registerBloodDetails(AvailableBloodSampleDTO dto);
	public List<AvailableBloodSampleDTO> fetchAllBloodSamples();
	public String updateBloodDetails(AvailableBloodSampleDTO dto);
	public String registerBloodReq(BloodRequestDTO dto, boolean flag);
	public List<BloodRequestDTO> fetchAllBloodReq(String hospRegId);
	
	
	public boolean existsByEmail(String email, Class<ReciverRegistration> class1);
	public boolean existsByRegId(String email, Class<HospitalRegistration> class1);

	

}
