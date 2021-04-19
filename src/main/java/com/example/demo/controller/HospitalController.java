package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<HospitalDetail>fetchHospital(){
		return hospitalService.fetchHospitals();
	}
}
