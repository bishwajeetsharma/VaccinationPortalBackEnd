package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.LoginCredentials;
import com.example.demo.service.AuthenticationService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/authenticate")
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;
	@PostMapping("/login")
	@ApiOperation(value = "API for authentication and logging", response = ResponseEntity.class)
	@CrossOrigin("*")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginCredentials credentials) throws Exception {
		AuthenticationResponse response = authenticationService.createAuthenticationToken(credentials);
		return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
	}
	
}
