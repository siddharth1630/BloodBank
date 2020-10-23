package com.internshala.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internshala.bo.AvailableBloodSampleBO;
import com.internshala.bo.BloodRequestBO;
import com.internshala.bo.HospitalBO;
import com.internshala.bo.ReciverBO;
import com.internshala.dao.HospitalDAO;
import com.internshala.dao.ReciverDAO;
import com.internshala.dto.AvailableBloodSampleDTO;
import com.internshala.dto.BloodRequestDTO;
import com.internshala.dto.HospitalDTO;
import com.internshala.dto.ReciverDTO;
import com.internshala.entity.HospitalRegistration;
import com.internshala.entity.ReciverRegistration;

@Service
public class BloodBankServiceImpl implements BloodBankService {
	
	@Autowired
	private ReciverDAO recvDao;
	
	@Autowired
	private HospitalDAO hospDao;

	@Override
	public String registerReciver(ReciverDTO dto) {
		ReciverBO bo=null;
		int result=0;
		bo=new ReciverBO();
		// copy properties from dto to bo
		BeanUtils.copyProperties(dto, bo);
		// call method
		result=recvDao.insertReciver(bo);
		return result==0?"Registration Failed":"Registration Success";
	}

	@Override
	public String registerHospitol(HospitalDTO dto) {
		HospitalBO bo=null;
		int result=0;
		bo=new HospitalBO();
		// copy properties from dto to bo
		BeanUtils.copyProperties(dto, bo);
		// call method
		System.out.println("ser"+bo.getHospName());
		result=hospDao.insertHospital(bo);
		return result==0?"Hospital Registration Failed":"Hospital Registration Success";
	}
	
	@Override
	public ReciverDTO fetchReciverDetail(String email) {
		ReciverBO bo=null;
		ReciverDTO dto=null;
		bo=new ReciverBO();
		dto=new ReciverDTO();
		// use bo
		bo=recvDao.getReciverDetails(email);
		// copy prop from bo to dto
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}
	
	@Override
	public HospitalDTO fetchHospitaDetail(String hospRegId) {
		HospitalBO bo=null;
		HospitalDTO dto=null;
		bo=new HospitalBO();
		dto=new HospitalDTO();
		// use bo
		bo=hospDao.gethospitalDetails(hospRegId);
		// copy prop from bo to dto
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}
	
	@Override
	public String registerBloodDetails(AvailableBloodSampleDTO dto) {
		AvailableBloodSampleBO bo=null;
		int result=0;
		bo=new AvailableBloodSampleBO();
		// copy properties from dto to bo
		BeanUtils.copyProperties(dto, bo);
		// call method
		result=hospDao.insertBlood(bo);
		return result==0?bo.getBloodGrpName()+" Blood adding is Failed":bo.getBloodGrpName()+" Blood Successfully added";
	}
	
	@Override
	public List<AvailableBloodSampleDTO> fetchAllBloodSamples() {
		List<AvailableBloodSampleBO> listBo=null;
		List<AvailableBloodSampleDTO> listdto=new ArrayList<AvailableBloodSampleDTO>();
		// call the dao method
		listBo=recvDao.getAllBloodSamples();
		// convert bo to dto and performing some bussiness logic
		listBo.forEach(bo->{
			AvailableBloodSampleDTO dto=new AvailableBloodSampleDTO();
			BeanUtils.copyProperties(bo, dto);
			listdto.add(dto);			
		});
		return listdto;
	}
	
	@Override
	public String updateBloodDetails(AvailableBloodSampleDTO dto) {
		AvailableBloodSampleBO bo=null;
		int result=0;
		bo=new AvailableBloodSampleBO();
		// copy properties from dto to bo
		BeanUtils.copyProperties(dto, bo);
		// call method
		result=hospDao.updateBlood(bo);
		return result==0?bo.getBloodGrpName()+" updation is failed":bo.getBloodGrpName()+" Successfully updated the blood data";
	}
	
	@Override
	public String registerBloodReq(BloodRequestDTO dto,boolean flag) {
		BloodRequestBO bo=null;
		int result=0;
		bo=new BloodRequestBO();
		// copy properties from dto to bo
		BeanUtils.copyProperties(dto, bo);
		// call method
		result=recvDao.insertBloodReq(bo,flag);
		return result==0?"Request Failed":"Successfully reqest for blood";
	}
	
	@Override
	public List<BloodRequestDTO> fetchAllBloodReq(String hospRegId) {
		List<BloodRequestBO> listBo=null;
		List<BloodRequestDTO> listdto=new ArrayList<BloodRequestDTO>();
		// call the dao method
		listBo=hospDao.getListOfBloodReq(hospRegId);
		// convert bo to dto and performing some bussiness logic
		listBo.forEach(bo->{
			BloodRequestDTO dto=new BloodRequestDTO();
			BeanUtils.copyProperties(bo, dto);
			listdto.add(dto);			
		});
		return listdto;
	}
	
	@Override
	public boolean existsByEmail(String email, Class<ReciverRegistration> class1) {
		return recvDao.existsByEmail(email,class1);
	}
	
	@Override
	public boolean existsByRegId(String regId, Class<HospitalRegistration> class1) {
		return hospDao.existsByRegId(regId,class1);
	}
}
