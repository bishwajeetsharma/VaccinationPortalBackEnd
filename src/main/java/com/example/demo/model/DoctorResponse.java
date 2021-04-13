/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */
public class DoctorResponse {

	private int did;
	private String firstname;
	private String lastname;
	private String contactno;
	private String regNo;

	public DoctorResponse(int did, String firstname, String lastname, String contactno, String regNo) {
		super();
		this.did = did;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contactno = contactno;
		this.regNo = regNo;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

}
