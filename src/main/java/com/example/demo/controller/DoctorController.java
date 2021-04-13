/**
 * 
 */
package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.Details;
import com.example.demo.model.DoctorRegistration;
import com.example.demo.model.DoctorResponse;
import com.example.demo.service.DoctorService;

import io.swagger.annotations.ApiOperation;

/**
 * @author PRATAP
 *
 */
@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorController {

	private static final Logger logger = LogManager.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "/registerdoctor", method = RequestMethod.POST)
	@ApiOperation(value = "API for Registering the Doctors to MRA portal", response = Details.class)
	public ResponseEntity<Details> register(@RequestBody DoctorRegistration doctorRegData) {

		logger.info("Starting Doctor Registration Process for username :: [{}]", doctorRegData.getAuth().getUsername());
		String message = "Registration successful for the username : " + doctorRegData.getAuth().getUsername();
		try {
			int status = doctorService.registerDoctor(doctorRegData);
			if (status <= 0) {
				logger.info("Couldn't save Doctor Object.Please check the error");
			}
			Details details = new Details(new Date(), message, "/doctor/registerDoctor");
			return new ResponseEntity<Details>(details, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception occured while persisting Doctor data :: [{}]", ex.getMessage());
			message = ex.getMessage();
		}
		Details details = new Details(new Date(), message, "/doctor/registerDoctor");
		return new ResponseEntity<Details>(details, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getDoctorsByCity", method = RequestMethod.GET)
	@ApiOperation(value = "API for Fetching all the doctors in a particular city", response = Details.class)
	public ResponseEntity<?> getDoctorsByCity(@RequestParam(required = true, value = "username") String username) {
		
		logger.info("Fetching the doctors located in the same city for the username :: [{}]", username);
		try {
			List<DoctorResponse> doctorsList = doctorService.getDoctorsByCity(username);
			return new ResponseEntity<List<DoctorResponse>>(doctorsList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception occured while persisting Doctor data :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/getDoctorsByCity");
			return new ResponseEntity<Details>(details, HttpStatus.BAD_REQUEST);
		}
	}
}