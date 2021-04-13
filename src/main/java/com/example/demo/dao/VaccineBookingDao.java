/**
 * 
 */
package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.VaccineBooking;

/**
 * @author PRATAP
 *
 */
public interface VaccineBookingDao extends CrudRepository<VaccineBooking, Integer> {

}
