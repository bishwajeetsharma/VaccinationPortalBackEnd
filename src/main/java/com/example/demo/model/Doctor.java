package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer did;
	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false)
	private String gender;
	@Column(unique = true, nullable = false)
	private String contactno;
	@Column(unique = true, nullable = false)
	private String regNo;
	@Column(nullable = false)
	private String dob;

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	// one to one mapping with Auth
	@OneToOne
	@JoinColumn(name = "did")
	private Auth auth;

	// many to one mapping with location
	@ManyToOne
	@JoinColumn(name = "lid", nullable = false)
	private Location location;

	// many to many mapping with hospital
	@ManyToMany
	@JoinTable(name = "doctor_hospital", joinColumns = @JoinColumn(name = "did"), inverseJoinColumns = @JoinColumn(name = "hid"))
	private List<Hospital> hospital;

	public Doctor() {
	}

	public Doctor(String firstname, String dob, String lastname, String registrationNumber, String contactno,
			String gender) {
		super();
		this.firstname = firstname;
		this.dob = dob;
		this.lastname = lastname;
		this.regNo = registrationNumber;
		this.contactno = contactno;
		this.gender = gender;
	}

	// getters and setters
	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRegNo() {
		return regNo;
	}
}
