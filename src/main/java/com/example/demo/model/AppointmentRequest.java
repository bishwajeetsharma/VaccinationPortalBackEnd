/**
 * 
 */
package com.example.demo.model;

import java.util.List;

/**
 * @author PRATAP
 *
 */
public class AppointmentRequest {

	private int bookingId;
	private String userName;
	private String doctorUsername;
	private String vaccineName;
	private List<Dosage> dosages;
	private String approved;
	private String comments;

	public AppointmentRequest() {
		super();
	}

	public AppointmentRequest(int bookingId, String userName, String doctorUsername, String vaccineName, List<Dosage> dosages,
			String approved, String comments) {
		super();
		this.bookingId = bookingId;
		this.userName = userName;
		this.doctorUsername = doctorUsername;
		this.vaccineName = vaccineName;
		this.dosages = dosages;
		this.approved = approved;
		this.comments = comments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public List<Dosage> getDosages() {
		return dosages;
	}

	public void setDosages(List<Dosage> dosages) {
		this.dosages = dosages;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

}
