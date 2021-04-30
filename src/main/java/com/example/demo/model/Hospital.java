package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = true, unique = true)
	private String contactno;

	// many to many mapping with doctor
	@ManyToMany(mappedBy = "hospital")
	private List<Doctor> doctor;

	// many to many mapping with vaccine
//	@ManyToMany(mappedBy = "hospital")
//	private List<Vaccine> vaccine;
	@OneToMany(mappedBy = "hid", cascade = CascadeType.ALL)
    private List<VaccineHospital> vaccineHospital = new ArrayList<>();

	// many to one mapping with location
	@ManyToOne
	@JoinColumn(name = "lid", nullable = false)
	private Location location;

	// getters and setters
	public List<Doctor> getDoctor() {
		return doctor;
	}

	public List<VaccineHospital> getVaccineHospital() {
		return vaccineHospital;
	}

	public void setVaccineHospital(List<VaccineHospital> vaccineHospital) {
		this.vaccineHospital = vaccineHospital;
	}

	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Hospital() {
	}

	public Hospital(String name, String contactno) {
		super();
		this.name = name;
		this.contactno = contactno;
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

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
}
