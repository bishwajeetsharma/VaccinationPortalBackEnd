/**
 * 
 */
package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Vaccine;

/**
 * @author PRATAP
 *
 */
public interface VaccineDao extends CrudRepository<Vaccine, Integer> {

	public static final String FETCH_VACCINES = "SELECT name FROM vaccine";

	public Vaccine findByName(String name);

	@Query(value = FETCH_VACCINES, nativeQuery = true)
	public Iterable<String> fetchVaccines();
}
