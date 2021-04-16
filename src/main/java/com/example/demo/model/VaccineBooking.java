/**
 * 
 */
package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToOne
	@JoinColumn(name = "uid", nullable = false)
	private User user;

	@OneToOne
	@JoinColumn(name = "did", nullable = false)
	private Doctor doctor;

	@OneToOne
	@JoinColumn(name = "vid", nullable = false)
	private Vaccine vaccine;

	@OneToOne
	@JoinColumn(name = "fileId", nullable = false)
	private FileDB fileDb;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private Date bookingDate;

	public VaccineBooking() {
		super();
	}

	public VaccineBooking(String status, Date bookingDate) {
		this.status = status;
		this.bookingDate = bookingDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FileDB getFileDb() {
		return fileDb;
	}

	public void setFileDb(FileDB fileDb) {
		this.fileDb = fileDb;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

}
