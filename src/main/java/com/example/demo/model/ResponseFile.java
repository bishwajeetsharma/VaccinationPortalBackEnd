/**
 * 
 */
package com.example.demo.model;

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
	private String bookingDate;
	private String status;

	public ResponseFile(int bookingId, String fileName, String url, String type, long size, String firstName,
			String lastName, String vaccine, String bookingDate, String status) {
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

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
