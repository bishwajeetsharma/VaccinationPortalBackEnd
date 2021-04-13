/**
 * 
 */
package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.Location;

/**
 * @author PRATAP
 *
 */
@Repository
public interface DoctorDao extends CrudRepository<Doctor, Integer> {

	public boolean existsByRegNo(String regNo);
	public boolean existsByContactno(String contactno);
	public Optional<List<Doctor>> findAllByLocation(Location location);
	public Doctor findByRegNo(String regNo);
}
