/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */

public class DoctorRegistration {
	
	private Doctor doctor;
	private Location location;
	private Auth auth;
	
	public DoctorRegistration() {
		super();
	}

	public DoctorRegistration(Doctor doctor, Location location, Auth auth) {
		super();
		this.doctor = doctor;
		this.location = location;
		this.auth = auth;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}
	
}
