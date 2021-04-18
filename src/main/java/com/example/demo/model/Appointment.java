/**
 * 
 */
package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author PRATAP
 *
 */
@Entity(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmentId;

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
	@JoinColumn(name = "hid", nullable = true)
	private Hospital hospital;

	@JoinColumn(name = "doseNo", nullable = true)
	private int doseNo;

	@JoinColumn(name = "time", nullable = true)
	private String time;

	@JoinColumn(name = "appointmentDate", nullable = true)
	private Date appointmentDate;

	@JoinColumn(name = "comments", nullable = false)
	private String comments;

	@JoinColumn(name = "status", nullable = false)
	private String status;

	public Appointment() {
		super();
	}

	public Appointment(User user, Doctor doctor, Vaccine vaccine, Hospital hospital, int doseNo, String time,
			Date appointmentDate, String comments, String status) {
		super();
		this.user = user;
		this.doctor = doctor;
		this.vaccine = vaccine;
		this.hospital = hospital;
		this.doseNo = doseNo;
		this.time = time;
		this.appointmentDate = appointmentDate;
		this.comments = comments;
		this.status = status;
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

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public int getDoseNo() {
		return doseNo;
	}

	public void setDoseNo(int doseNo) {
		this.doseNo = doseNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

}
