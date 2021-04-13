package com.example.demo.controller;

import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.Details;
import com.example.demo.model.UserRegistration;
import com.example.demo.model.VaccineRegistration;
import com.example.demo.service.UserRegistrationService;
import com.example.demo.service.VaccineBookingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserRegistrationService service;
	
	@Autowired
	private VaccineBookingService vaccineBookingService;

	@PostMapping("/registeruser")
	@ApiOperation(value = "API for Registering the Users to MRA portal", response = Details.class)
	public ResponseEntity<Details> register(@RequestBody UserRegistration userreg) {
		service.registerUser(userreg);

		Details details = new Details(new Date(), "Registration successful", "/user/registeruser");
		return new ResponseEntity<Details>(details, HttpStatus.OK);

	}

	@RequestMapping("/dashboard")
	@ApiOperation(value = "API for rendering user dashboard", response = Details.class)
	public ResponseEntity<Details> dashboard() {
		Details details = new Details(new Date(), "dasboard works", "/user/dashboard");
		return new ResponseEntity<Details>(details, HttpStatus.OK);
	}

	@RequestMapping(value = "/vaccineBooking", method = RequestMethod.POST)
	@ApiOperation(value = "API for application of booking of Vaccine by user", response = Details.class)
	public ResponseEntity<?> vaccineBooking(@RequestParam(name = "file", required = true) MultipartFile file,
			@RequestParam(name = "username", required = true) String userName,
			@RequestParam(name = "vaccineName", required = true) String vaccineName,
			@RequestParam(name = "docRegNo", required = true) String docRegNo) {
		logger.info("Proceeding with storing the file uploaded by the user :: [{}] with file name : [{}]", userName,
				file.getOriginalFilename());
		try {
			VaccineRegistration vaccineReg = new VaccineRegistration(userName, vaccineName, docRegNo);
			vaccineBookingService.vaccineBooking(vaccineReg, file);
		} catch (Exception ex) {
			logger.error("Exception occured while storing the file :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), "File Upload Failed", "/user/vaccineBooking");
			return new ResponseEntity<Details>(details, HttpStatus.REQUEST_TIMEOUT);
		}
		return null;
	}
}
