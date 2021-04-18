/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */
public class Dosage {

	private int doseNo;
	private String date;
	private String time;
	private String hospital;

	public Dosage() {
		super();
	}

	public Dosage(int doseNo, String date, String time, String hospital) {
		super();
		this.doseNo = doseNo;
		this.date = date;
		this.time = time;
		this.hospital = hospital;
	}

	public int getDoseNo() {
		return doseNo;
	}

	public void setDoseNo(int doseNo) {
		this.doseNo = doseNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

}
