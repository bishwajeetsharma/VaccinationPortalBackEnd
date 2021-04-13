package com.example.demo.model;

public class AuthenticationResponse {

	private final String firstname;
	private final String lastname;
	private final int id;
	private final String username;
	private final String password;
	private final String jwt;
	private final long expirationDate;
    private final String role;
	

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getId() {
		return id;
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

	public AuthenticationResponse(String firstname, String lastname, int id, String username, String password,
			String jwt, long expirationDate,String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.username = username;
		this.password = password;
		this.jwt = jwt;
		this.expirationDate = expirationDate;
		this.role=role;
	}

	public String getRole() {
		return role;
	}

	

}
