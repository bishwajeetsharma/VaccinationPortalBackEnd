package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HospitalDetail;
import com.example.demo.service.HospitalService;

@RestController
@RequestMapping("/hospital")
@CrossOrigin
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;
	@RequestMapping("/fetchHospital")
	public List<HospitalDetail>fetchHospital(@RequestParam("state") String state,@RequestParam("city")String city) throws Exception{
		if(hospitalService.fetchHospitals(state,city).isEmpty())
			throw new Exception("Sorry! We are currently not connected with any hospitals in this location.");
		return hospitalService.fetchHospitals(state,city);
	}
}
