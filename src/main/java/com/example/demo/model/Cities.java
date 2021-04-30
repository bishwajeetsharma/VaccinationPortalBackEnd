/**
 * 
 */
package com.example.demo.model;

/**
 * @author PRATAP
 *
 */
public class Cities {

	private String city;
	private String region;
	private String country;

	public Cities() {
		super();
	}

	public Cities(String city, String region, String country) {
		super();
		this.city = city;
		this.region = region;
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
