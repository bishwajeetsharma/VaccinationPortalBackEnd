package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String state;
	@Column(nullable=false)
	private String city;
	
	
	//one to many mapping with hospital
		@OneToMany(mappedBy = "location")
		private List<Hospital> hospital;
		
		//one to many mapping with doctor
		@OneToMany(mappedBy = "location")
		private List<Doctor> doctor;
		
		//one to many mapping with user
		@OneToMany(mappedBy="location")
		private List<User> user;
		
		
		//getters and setters
	
	public List<Hospital> getHospital() {
		return hospital;
	}
	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}
	public List<Doctor> getDoctor() {
		return doctor;
	}
	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public Location() {}
	public Location(String state, String city) {
		super();
		this.state = state;
		this.city = city;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
