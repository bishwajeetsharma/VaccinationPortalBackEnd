/**
 * 
 */
package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Appointment;

/**
 * @author PRATAP
 *
 */
@Repository
public interface AppointmentDao extends CrudRepository<Appointment, Integer> {

}
