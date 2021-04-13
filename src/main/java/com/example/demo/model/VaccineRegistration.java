/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */
public class VaccineRegistration {

	private String username;
	private String vaccineName;
	private String docRegNo;

	public VaccineRegistration(String username, String vaccineName, String docRegNo) {
		super();
		this.username = username;
		this.vaccineName = vaccineName;
		this.docRegNo = docRegNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getDocRegNo() {
		return docRegNo;
	}

	public void setDocRegNo(String docRegNo) {
		this.docRegNo = docRegNo;
	}

}
