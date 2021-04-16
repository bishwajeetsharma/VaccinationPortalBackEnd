/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */
public class PendingApprovalsResponse {

	private int bookingId;
	private String firstName;
	private String lastName;
	private String vaccineName;
	private String fileUrl;
	private String status;
	private String type;
	private long size;
	private String name;

	public PendingApprovalsResponse() {
		super();
	}

	public PendingApprovalsResponse(int bookingId, String firstName, String lastName, String vaccineName,
			String fileUrl, String status, long size, String type, String name) {
		super();
		this.bookingId = bookingId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.vaccineName = vaccineName;
		this.fileUrl = fileUrl;
		this.status = status;
		this.size = size;
		this.type = type;
		this.name = name;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
