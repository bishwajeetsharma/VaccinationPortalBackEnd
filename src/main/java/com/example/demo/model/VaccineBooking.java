/**
 * 
 */
package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author PRATAP
 *
 */
@Entity
@Table(name = "vaccine_booking")
public class VaccineBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@Column(nullable = false, unique = true)
	private String fileId;

	@OneToOne
	@JoinColumn(name = "uid", nullable = false)
	private User user;

	@OneToOne
	@JoinColumn(name = "did", nullable = false)
	private Doctor doctor;
	
	@OneToOne
	@JoinColumn(name = "vid", nullable = false)
	private Vaccine vaccine;
	
	@Lob
	@Column(name = "file", nullable = false)
	private byte[] file;
	
	@Column(nullable = false)
	private String status;

	public VaccineBooking() {
		super();
	}

	public VaccineBooking(byte[] file, String status, String fileId) {
		super();
		this.file = file;
		this.status = status;
		this.fileId = fileId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}	
}
