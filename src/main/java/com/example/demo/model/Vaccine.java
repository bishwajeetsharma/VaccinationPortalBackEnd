package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = false, nullable = false)
	private Integer dosage;

	// many to many mapping with hospital
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "vaccine_hospital", joinColumns = @JoinColumn(name = "vid"), inverseJoinColumns = @JoinColumn(name = "hid"))
//	private List<Hospital> hospital;
	@OneToMany(mappedBy = "vid", cascade = CascadeType.ALL)
	private List<VaccineHospital> vaccineHospital;

	public Vaccine() {
		super();
	}

	// getters and setters
	public Vaccine(String name, Integer dosage) {
		super();
		this.name = name;
		this.dosage = dosage;
	}

	public List<VaccineHospital> getVaccineHospital() {
		return vaccineHospital;
	}

	public void setVaccineHospital(List<VaccineHospital> vaccineHospital) {
		this.vaccineHospital = vaccineHospital;
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

	public Integer getDosage() {
		return dosage;
	}

	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}
}
