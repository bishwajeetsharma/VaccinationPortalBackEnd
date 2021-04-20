package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.HospitalDao;
import com.example.demo.dao.LocationDao;
import com.example.demo.model.HospitalDetail;
import com.example.demo.model.Location;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitaldao;

	@Autowired
	private LocationDao locationdao;
	public List<HospitalDetail> fetchHospitals(String state,String city) {
	    Optional<Location>location=locationdao.findByStateAndCity(state, city);
	    if(location.isPresent())
		return (List<HospitalDetail>) (Object) hospitaldao.fetchHospitals(location.get().getId().intValue());
	    else
	    	return new ArrayList<HospitalDetail>();
	}
}
