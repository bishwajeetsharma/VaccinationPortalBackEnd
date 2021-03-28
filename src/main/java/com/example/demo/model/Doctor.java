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
	private Integer id;
	@Column(nullable=false)
	private String name;
	@Column(unique=true,nullable=false)
	private String registrationno;
	@Column(unique=true,nullable=false)
	private String contactno;
	private String gender;

	//one to one mapping with Auth
	@OneToOne
	@JoinColumn(name="did")
	private Auth auth;
	
	//many to one mapping with location
	@ManyToOne
	@JoinColumn(name="lid",nullable=false)
	private Location location;
	
	//many to many mapping with hospital
	@ManyToMany
	@JoinTable(name="doctor_hospital",joinColumns = @JoinColumn(name="did"),inverseJoinColumns = @JoinColumn(name="hid"))
	private List<Hospital> hospital;
	
	
	
	//getters and setters
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
	public Doctor() {}
    public Doctor(String name, String registrationno, String contactno, String gender) {
		super();
		this.name = name;
		this.registrationno = registrationno;
		this.contactno = contactno;
		this.gender = gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistrationno() {
		return registrationno;
	}
	public void setRegistrationno(String registrationno) {
		this.registrationno = registrationno;
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
	

}
