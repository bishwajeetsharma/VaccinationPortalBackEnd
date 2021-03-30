package com.example.demo.controller;





import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Exception.Details;
import com.example.demo.model.UserRegistration;
import com.example.demo.service.UserRegistrationService;

@org.springframework.web.bind.annotation.RestController
public class UserController {

	@Autowired
	private UserRegistrationService service;
	@PostMapping("/registeruser")
	@CrossOrigin
	public  ResponseEntity<Details> register(@RequestBody UserRegistration userreg)
	{
		service.registerUser(userreg);
		
		Details details=new Details(new Date(),"Registration successful","/registeruser");
		return new ResponseEntity(details,HttpStatus.OK);
		
	}
}
