/**
 * 
 */
package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Hospital;
import com.example.demo.model.Location;

/**
 * @author PRATAP
 *
 */
@Repository
public interface HospitalDao extends CrudRepository<Hospital, Integer> {

	public Hospital findByNameAndLocation(String name, Location loc);
}
