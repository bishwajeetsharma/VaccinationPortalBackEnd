/**
 * 
 */
package com.example.demo.dao;

import java.util.List;

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
}
