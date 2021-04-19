package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.message.ResponseMessage;
import com.example.demo.model.VaccineDetail;
import com.example.demo.service.VaccineService;

@RestController
@RequestMapping("/vaccine")
@CrossOrigin
public class VaccineController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	private VaccineService vaccineservice;

	@RequestMapping("/fetchvaccine")
	public List<String> fetchVaccines() {
		return vaccineservice.fetchVaccines();
	}

	@PostMapping("/saveVaccine")
	public ResponseEntity<ResponseMessage> saveVaccine(@RequestBody VaccineDetail vaccinedetail) {
		logger.info("Updation for vaccine ::[{}] for hospital_id :: [{}] with dosage_count ::[{}] started",
				vaccinedetail.getVaccineName(), vaccinedetail.getHid(), vaccinedetail.getDosage());
		try {
			vaccineservice.saveVaccine(vaccinedetail.getVaccineName(), vaccinedetail.getDosage(),
					vaccinedetail.getHid());
		} catch (Exception e) {
			logger.error("Exception occured while updating the vaccine ::[{}]", e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
		}
		logger.info("Vaccine ::[{}] for hospital_id :: [{}] with dosage_count ::[{}] successfully updated",
				vaccinedetail.getVaccineName(), vaccinedetail.getHid(), vaccinedetail.getDosage());
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Vaccine Registered!"));

	}
}
