/**
 * 
 */
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author PRATAP
 *
 */
@Entity
@Table(name = "vaccine_hospital")
public class VaccineHospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "vid")
	private Vaccine vid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "hid")
	private Hospital hid;

	@JoinColumn(name = "vaccines")
	private int vaccinesAvailable;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hid == null) ? 0 : hid.hashCode());
		result = prime * result + id;
		result = prime * result + vaccinesAvailable;
		result = prime * result + ((vid == null) ? 0 : vid.hashCode());
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vaccine getVid() {
		return vid;
	}

	public void setVid(Vaccine vid) {
		this.vid = vid;
	}

	public Hospital getHid() {
		return hid;
	}

	public void setHid(Hospital hid) {
		this.hid = hid;
	}

	public int getVaccinesAvailable() {
		return vaccinesAvailable;
	}

	public void setVaccinesAvailable(int vaccinesAvailable) {
		this.vaccinesAvailable = vaccinesAvailable;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VaccineHospital other = (VaccineHospital) obj;
		if (hid == null) {
			if (other.hid != null)
				return false;
		} else if (!hid.equals(other.hid))
			return false;
		if (id != other.id)
			return false;
		if (vaccinesAvailable != other.vaccinesAvailable)
			return false;
		if (vid == null) {
			if (other.vid != null)
				return false;
		} else if (!vid.equals(other.vid))
			return false;
		return true;
	}

}
