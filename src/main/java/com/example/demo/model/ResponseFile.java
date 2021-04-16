/**
 * 
 */
package com.example.demo.model;

import java.sql.Date;

/**
 * @author PRATAP
 *
 */
public class ResponseFile {

	private int bookingId;
	private String fileName;
	private String url;
	private String type;
	private long size;
	private String firstName;
	private String lastName;
	private String vaccine;
	private Date bookingDate;
	private String userName;
	private String status;
	private int dosage;

	public ResponseFile(int bookingId, String fileName, String url, String type, long size, String firstName,
			String lastName, String vaccine, Date bookingDate, String status, String userName, int dosage) {
		this.bookingId = bookingId;
		this.fileName = fileName;
		this.url = url;
		this.type = type;
		this.size = size;
		this.firstName = firstName;
		this.lastName = lastName;
		this.vaccine = vaccine;
		this.bookingDate = bookingDate;
		this.status = status;
		this.userName = userName;
		this.dosage = dosage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}
}
