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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.message.ResponseMessage;
import com.example.demo.model.DeleteVaccine;
import com.example.demo.model.VaccineDetail;
import com.example.demo.service.VaccineService;

@RestController
@RequestMapping("/vaccine")
@CrossOrigin("*")
public class VaccineController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	private VaccineService vaccineservice;

	@RequestMapping(value = "/fetchvaccine", method = RequestMethod.GET)
	public List<String> fetchVaccines() {
		return vaccineservice.fetchVaccines();
	}

	@PostMapping("/saveVaccine")
	public ResponseEntity<ResponseMessage> saveVaccine(@RequestBody VaccineDetail vaccinedetail) {
		logger.info("Updation for vaccine ::[{}] for hospital_id :: [{}] with dosage_count ::[{}] started",
				vaccinedetail.getVaccineName(), vaccinedetail.getHid(), vaccinedetail.getDosage());
		try {
			vaccineservice.saveVaccine(vaccinedetail);
		} catch (Exception e) {
			logger.error("Exception occured while updating the vaccine ::[{}]", e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
		}
		logger.info("Vaccine ::[{}] for hospital_id :: [{}] with dosage_count ::[{}] successfully updated",
				vaccinedetail.getVaccineName(), vaccinedetail.getHid(), vaccinedetail.getDosage());
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Vaccine Registered!"));

	}

	@RequestMapping(value = "/fetchVaccinebyHid", method = RequestMethod.GET)
	public List<VaccineDetail> fetchVaccineByHid(@RequestParam("hospitalId") String hospitalId) throws Exception {
		Integer hid = Integer.valueOf(Integer.parseInt(hospitalId));
		if (vaccineservice.fetchVaccineByHid(hid).isEmpty())
			throw new Exception("No vaccines are available at this hospital");
		else
			return vaccineservice.fetchVaccineByHid(hid);
	}

	@PostMapping("/deleteVaccine")
	public ResponseEntity<ResponseMessage> deleteVaccine(@RequestBody DeleteVaccine deletevaccine) {
		String msg = "";
		try {
			msg = vaccineservice.deleteVaccine(deletevaccine.getHid(), deletevaccine.getVid());
		} catch (Exception ex) {
			if (msg == null || msg.equalsIgnoreCase(""))
				msg = ex.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(msg));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(msg));
	}
}
