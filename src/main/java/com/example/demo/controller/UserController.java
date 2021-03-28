package com.example.demo.controller;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.UserRegistration;
import com.example.demo.service.UserRegistrationService;

@org.springframework.web.bind.annotation.RestController
public class UserController {

	@Autowired
	private UserRegistrationService service;
	@PostMapping("/registeruser")
	@Transactional
	@CrossOrigin
	public String register(@RequestBody UserRegistration userreg)
	{
		service.registerUser(userreg);
		
		return userreg.getUser().getFirstname()+" ,you are successfully registered!";
	}
}
