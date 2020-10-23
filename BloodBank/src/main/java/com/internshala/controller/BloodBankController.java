package com.internshala.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.internshala.dto.AvailableBloodSampleDTO;
import com.internshala.dto.BloodRequestDTO;
import com.internshala.dto.HospitalDTO;
import com.internshala.dto.ReciverDTO;
import com.internshala.entity.AvailableBloodSample;
import com.internshala.entity.BloodRequest;
import com.internshala.entity.HospitalRegistration;
import com.internshala.entity.ReciverRegistration;
import com.internshala.model.Login;
import com.internshala.service.BloodBankService;
import com.internshala.validator.HospitalValidator;
import com.internshala.validator.ReciverValidator;
import com.mysql.cj.Session;

@Controller
@SessionAttributes({"email"})
public class BloodBankController {
	@Autowired
	ReciverValidator validator;
	
	@Autowired
	HospitalValidator hValidator;
	
	@Autowired
	BloodBankService service;

	@GetMapping("/home")
	public String home(@ModelAttribute("recvReg") ReciverRegistration recvReg,SessionStatus status) {
		status.setComplete();
		return "reciverRegis";
	}
	
	@ModelAttribute("recvCities")
	public List<String> populateCities (){
		List<String> recvCities=List.of("MUMBAI","PUNE","CHENNAI","KOLKATA","RANCHI","PATNA","BILASPUR","BOKARO","BANGLORE","HYDERABAD");
		return  recvCities;
	}
	
	@ModelAttribute("bloodGrpNames")
	public List<String> populateBloodGroups (){
		List<String> bloodGrpNames=List.of("A+","B+","AB+","O+","O-","A-","B-","AB-");
		return  bloodGrpNames;
	}
	
	@PostMapping("/home")
	public String registerEmp(@Valid @ModelAttribute("recvReg") ReciverRegistration recvReg
									,BindingResult errors,RedirectAttributes redirect) {
		ReciverDTO dto=null;
		String result=null;
	
		dto=new ReciverDTO();
	
		if(recvReg.isJsFlag()==false)	
			// perform form validation
			if(validator.supports(recvReg.getClass()))
				validator.validate(recvReg, errors);

		// if form validation  contain error then it will redirct
		if(errors.hasErrors())
			return "reciverRegis";		
		
		
		// copy model to dto
		BeanUtils.copyProperties(recvReg, dto);
		// call serivice method
		try {
			result=service.registerReciver(dto);
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "Something Went wrong");
			return "redirect:home";	 
		}
		//set model
		redirect.addFlashAttribute("registerMsg", result);	
		return "redirect:home";	 
	}
	//=============================================================================
	@GetMapping("/hospReg")
	public String hospitalRegis(@ModelAttribute("hospReg") HospitalRegistration hosp) {
		return "hospRegis";
	}
	
	@PostMapping("/hospReg")
	public String hospitalReg(@Valid @ModelAttribute("hospReg") HospitalRegistration hospReg
									,BindingResult errors,RedirectAttributes redirect ) {
		HospitalDTO dto=null;
		String result=null;
	
		dto=new HospitalDTO();
	
		if(hospReg.isJsFlag()==false)	
			// perform form validation
			if(hValidator.supports(hospReg.getClass())) {
				hValidator.validate(hospReg, errors);
			}

		
		// if form validation  contain error then it will redirct
		if(errors.hasErrors())
			return "hospRegis";		
			
		
		// copy model to dto
		BeanUtils.copyProperties(hospReg, dto);
		// call serivice method
		try {
			result=service.registerHospitol(dto);
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "Something Went wrong");
			return "redirect:home";	 
		}
		//set model

		redirect.addFlashAttribute("registerMsg", result);	
		return "redirect:home";	 
	}
	
	//=============================================================================
	@GetMapping("/login")
	public String recvLogin(@ModelAttribute("login") Login login) {
		return "login";
	}
	
	@PostMapping("/login")
	public String loggidIn(RedirectAttributes redirect,@ModelAttribute("login") Login login,BindingResult errors,
								Model model) {
		if(login.getEmail().isEmpty() || login.getPass().isEmpty()) {
			redirect.addFlashAttribute("registerMsg", "Field not be empty");	
			return "redirect:login";
		}
		else {
			if(login.getEmail().endsWith(".com")) {
				try {
					ReciverDTO dto=service.fetchReciverDetail(login.getEmail());
					if(dto.getRecvEmail().equalsIgnoreCase(login.getEmail()) && dto.getRecvPass().equals(login.getPass())) {
						redirect.addFlashAttribute("registerMsg", dto.getRecvName());	
						model.addAttribute("email", dto.getRecvEmail());	// session for store email
						return "reciver-loggedIn";
					}
					else {
						redirect.addFlashAttribute("registerMsg", "Email/hosp & pass doesn't match");	
						return "redirect:login";
					}
				}
				catch(EmptyResultDataAccessException erdae) {
					erdae.printStackTrace();
					redirect.addFlashAttribute("registerMsg", login.getEmail()+"   is not registered");	
					return "redirect:login";
				}
				catch(Exception e) {
					e.printStackTrace();
					redirect.addFlashAttribute("registerMsg", "something went wrong");	
					return "redirect:login";
				}
			}
			else {
				try {
					HospitalDTO dto=service.fetchHospitaDetail(login.getEmail());
					if(dto.getHospRegId().equalsIgnoreCase(login.getEmail()) && dto.getHospPass().equals(login.getPass())) {
						model.addAttribute("email", dto.getHospRegId());	// session for hospRegID
						redirect.addFlashAttribute("registerMsg", dto.getHospName());	
						return "hospital-loggedIn";
					}
					else {
						redirect.addFlashAttribute("registerMsg", "Email/hosp & pass doesn't match");	
						return "redirect:login";
					}
				}
				catch(EmptyResultDataAccessException erdae) {
					erdae.printStackTrace();
					redirect.addFlashAttribute("registerMsg", login.getEmail()+"   is not registered");	
					return "redirect:login";
				}
				catch(Exception e) {
					e.printStackTrace();
					redirect.addFlashAttribute("registerMsg", "something went wrong");	
					return "redirect:login";
				}
			}
		}
			
	}
	@GetMapping("/back")
	public String back() {
		return "reciver-loggedIn";
	}
	//================================================================================================
	@GetMapping("/addBlood")
	public String addBlood(@ModelAttribute("avlBlood") AvailableBloodSample avlBloodSample) {
		return "add-blood";
	}
	
	@ModelAttribute("qty")
	public List<String> bloodQty (){
		List<String> qty=List.of("1","2","3","4","5","6","7","8","9","10");
		return  qty;
	}
	
	@PostMapping("/addBlood")
	public String addBlood(RedirectAttributes redirect ,@ModelAttribute("addBlood") AvailableBloodSample avlBlood) {
		AvailableBloodSampleDTO dto=null;
		String result=null;
		dto=new AvailableBloodSampleDTO();		
		// copy model to dto
		BeanUtils.copyProperties(avlBlood, dto);
		// call serivice method
		List<AvailableBloodSampleDTO> listDto=null;
		try {
			 listDto=service.fetchAllBloodSamples();
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "Something Went wrong");
			return "redirect:login";	 
		}
		
		String bGrpName=dto.getBloodGrpName();
		String hRegId=dto.getHospRegId();
		List<Integer> li=new ArrayList<Integer>();
		listDto.forEach(dt->{
			if(dt.getBloodGrpName().equals(bGrpName) && dt.getHospRegId().equals(hRegId)) {
				li.add(1);
			}
		});
		if(li.size()==0) {
			try {
				result=service.registerBloodDetails(dto);
			}
			catch(Exception e) {
				e.printStackTrace();
				redirect.addFlashAttribute("registerMsg", "Something Went wrong");
				return "redirect:login";	 
			}
		}
		else {
			try {
				result=service.updateBloodDetails(dto);
			}
			catch(Exception e) {
				e.printStackTrace();
				redirect.addFlashAttribute("registerMsg", "Something Went wrong");
				return "redirect:login";	 
			}
		}
		//set model
		redirect.addFlashAttribute("registerMsg", result);	
		return "hospital-loggedIn";
		
	}
	//=================================================================================================
	
	@GetMapping("/avalSample")
	public String getAllSample(Map<String, Object> map,@ModelAttribute("bReq") BloodRequest req  ,RedirectAttributes redirect) {
		List<AvailableBloodSampleDTO> listDto=null;
		List<HospitalDTO> listhospDto=new ArrayList<HospitalDTO>();
		// use service
		try {
			listDto=service.fetchAllBloodSamples();
			
			for(int i = 0; i < listDto.size(); i++)
			{
				HospitalDTO dto=new HospitalDTO();
				dto=service.fetchHospitaDetail(listDto.get(i).getHospRegId());
			    listhospDto.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "no samples is there");
			return "redirect:home";
		}
		map.put("bloodInfo", listDto);
		map.put("listHospInfo",listhospDto);
		return "show_blood_samples";
	}
	
	@PostMapping("/avalSample")
	public String reqSample(RedirectAttributes redirect  ,@ModelAttribute("bReq") BloodRequest req,@RequestParam("avlqty") int avlQnt) {
		BloodRequestDTO dto=null;
		String result=null;
		boolean flag=false;
		if(avlQnt==req.getQty()) {
			flag=true;
		}
		System.out.println("ava "+avlQnt+"  "+req.getQty());
		if(req.getQty()==0) {
			redirect.addFlashAttribute("registerMsg", "Qty not be zero");
			return "redirect:avalSample";
		}
		else if(avlQnt<req.getQty()) {
			redirect.addFlashAttribute("registerMsg", "Qty not be greater than available");
			return "redirect:avalSample";
		}
		else {
			try {
				dto=new BloodRequestDTO();
				BeanUtils.copyProperties(req, dto);
				result=service.registerBloodReq(dto,flag);		
			}
			catch(Exception e) {
				e.printStackTrace();
				redirect.addFlashAttribute("registerMsg", "something went wrong");
				return "redirect:avalSample";
			}
		}
		redirect.addFlashAttribute("registerMsg", result);
		return "redirect:avalSample";
	}
	//==================================================================================================
	@GetMapping("/viewReq")
	public String viewReq(Map<String, Object> map,RedirectAttributes redirect ,Model model) {
		String hospRegId=(String) model.getAttribute("email");
		List<BloodRequestDTO> listDto=null;
		List<ReciverDTO> listRecvDto=new ArrayList<ReciverDTO>();
		// use service
		try {
			listDto=service.fetchAllBloodReq(hospRegId);
			for(int i = 0; i < listDto.size(); i++)
			{
				ReciverDTO dto=new ReciverDTO();
				dto=service.fetchReciverDetail(listDto.get(i).getRecvEmail());
			    listRecvDto.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "No Blood Req is there");
			return "redirect:home";
		}
		map.put("bloodReqInfo", listDto);
		map.put("recvDetail", listRecvDto);
		map.put("brReq",new BloodRequest() );
		return "show_blood_request";
	}
	@GetMapping("/hback")
	public String hback() {
		return "hospital-loggedIn";
	}
	//=====================================================================================
	@PostMapping("/search")
	public String search(Map<String, Object> map,@ModelAttribute("bReq") BloodRequest req  ,RedirectAttributes redirect) {
		List<HospitalDTO> listhospDto=new ArrayList<HospitalDTO>();
		
		// use service
		try {
			List<AvailableBloodSampleDTO> listDto=service.fetchAllBloodSamples();

			List<AvailableBloodSampleDTO> filteredList = listDto.stream()
				      .filter(sample -> 
				          sample.getBloodGrpName().contentEquals(req.getBloodGrpName()))
				        .collect(Collectors.toList());
			
			
			for(int i = 0; i < filteredList.size(); i++)
			{
				HospitalDTO dto=new HospitalDTO();
				dto=service.fetchHospitaDetail(filteredList.get(i).getHospRegId());
			    listhospDto.add(dto);
			}
			map.put("bloodInfo", filteredList);
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "no samples is there");
			return "redirect:home";
		}
		
		map.put("listHospInfo",listhospDto);
		return "show_blood_samples";
	}
	
	
	@PostMapping("/viewReq")
	public String viewReqSearch(Map<String, Object> map,@ModelAttribute("brReq") BloodRequest req ,RedirectAttributes redirect ,Model model) {
		String hospRegId=(String) model.getAttribute("email");
		List<BloodRequestDTO> listDto=null;
		List<ReciverDTO> listRecvDto=new ArrayList<ReciverDTO>();
	
		// use service
		try {
			listDto=service.fetchAllBloodReq(hospRegId);

			//=======================================
			List<BloodRequestDTO> filteredList = listDto.stream()
				      .filter(sample -> 
				          sample.getBloodGrpName().contentEquals(req.getBloodGrpName()))
				        .collect(Collectors.toList());
			//=========================
			for(int i = 0; i < filteredList.size(); i++)
			{
				ReciverDTO dto=new ReciverDTO();
				dto=service.fetchReciverDetail(filteredList.get(i).getRecvEmail());
			    listRecvDto.add(dto);
			}
			map.put("bloodReqInfo", filteredList);
			System.out.println(filteredList.size());
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("registerMsg", "No Blood Req is there");
			return "redirect:home";
		}
		
		map.put("recvDetail", listRecvDto);
		System.out.println(listRecvDto.size());
		map.put("brReq",new BloodRequest() );
		return "show_blood_request";
	}

}
