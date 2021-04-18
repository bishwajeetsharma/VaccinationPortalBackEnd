package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.VaccineDao;

@Service
public class VaccineService {

	@Autowired
	private VaccineDao vaccinedao;

	public List<String> fetchVaccines() {
		List<String> vaccines = (List<String>) vaccinedao.fetchVaccines();
		return vaccines;
	}
}
