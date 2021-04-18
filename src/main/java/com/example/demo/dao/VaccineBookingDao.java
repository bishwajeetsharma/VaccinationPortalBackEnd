/**
 * 
 */
package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.VaccineBooking;

/**
 * @author PRATAP
 *
 */
@Repository
public interface VaccineBookingDao extends CrudRepository<VaccineBooking, Integer> {

	public List<VaccineBooking> findByDoctor(Doctor doc);
	public static final String CHECK_VACCINE_STATUS="SELECT * FROM vaccine_booking WHERE uid=?1 and vid=?2";
	@Query(value=CHECK_VACCINE_STATUS,nativeQuery=true)
	public Optional<VaccineBooking> checkStatus(int uid,int vid);
}
