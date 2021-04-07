package com.example.demo.model;

public class AuthenticationResponse {

	private final String username;
	private final String password;
	private final String jwt;
	private final long expirationDate;

	public AuthenticationResponse(String username, String password, String jwt, long expdate) {
		super();
		this.username = username;
		this.password = password;
		this.jwt = jwt;
		this.expirationDate = expdate;
	}

	public String getJwt() {
		return jwt;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public long getExpirationDate() {
		return expirationDate;
	}

}
