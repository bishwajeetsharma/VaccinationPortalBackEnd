/**
 * 
 */
package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	public static final String FETCH_HOSPITAL="SELECT id,name FROM hospital WHERE lid=?1";
	@Query(value=FETCH_HOSPITAL,nativeQuery=true)
	public List<Object[]>fetchHospitals(int id);
}
