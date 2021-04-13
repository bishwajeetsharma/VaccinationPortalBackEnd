package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.LoginCredentials;

import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.UserDetailsClass;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailServiceClass userDetailsService;

	@Autowired
	private JwtUtils jwtTokenUtil;

	public AuthenticationResponse createAuthenticationToken(LoginCredentials credentials) throws Exception {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

		final UserDetailsClass userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());

		final String jwt = jwtTokenUtil.generatetoken(userDetails);
		if (userDetails.isEnabled()) {
			return new AuthenticationResponse(userDetails.getFirstname(), userDetails.getLastname(),
					userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), jwt,
					jwtTokenUtil.extractExpirartion(jwt).getTime(),userDetails.getRole());
		} else
			throw new Exception("This account is inactive");
	}
}
