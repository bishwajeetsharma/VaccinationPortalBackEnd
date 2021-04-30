package com.example.demo.model;

public class VaccineDetail {

	private String vaccineName;
	private int hid;
	private int dosage;
	private int noOfVaccines;

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public int getNoOfVaccines() {
		return noOfVaccines;
	}

	public void setNoOfVaccines(int noOfVaccines) {
		this.noOfVaccines = noOfVaccines;
	}

	public VaccineDetail(String vaccineName, int hid, int dosage, int noOfVaccines) {
		super();
		this.vaccineName = vaccineName;
		this.hid = hid;
		this.dosage = dosage;
		this.noOfVaccines = noOfVaccines;
	}

}
