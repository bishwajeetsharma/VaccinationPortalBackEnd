/**
 * 
 */
package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Vaccine;

/**
 * @author PRATAP
 *
 */
public interface VaccineDao extends CrudRepository<Vaccine, Integer> {

	public Vaccine findByName(String name);
}
