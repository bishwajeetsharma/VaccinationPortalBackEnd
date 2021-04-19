package com.example.demo.model;

public class VaccineDetail {

	private String vaccineName;
	private int hid;
	private int dosage;

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

	public VaccineDetail(String vaccineName, int hid, int dosage) {
		super();
		this.vaccineName = vaccineName;
		this.hid = hid;
		this.dosage = dosage;
	}

}
