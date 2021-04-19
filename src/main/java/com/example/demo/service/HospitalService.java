package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.HospitalDao;
import com.example.demo.model.HospitalDetail;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitaldao;

	public List<HospitalDetail> fetchHospitals() {
		return (List<HospitalDetail>) (Object) hospitaldao.fetchHospitals();
	}
}
