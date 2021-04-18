/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */
public class HospitalResponse {

	private int hospitalId;
	private String hospitalName;

	public HospitalResponse(int hospitalId, String hospitalName) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
