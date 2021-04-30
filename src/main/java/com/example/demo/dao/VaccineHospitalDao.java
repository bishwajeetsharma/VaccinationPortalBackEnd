/**
 * 
 */
package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Hospital;
import com.example.demo.model.Vaccine;
import com.example.demo.model.VaccineHospital;

/**
 * @author PRATAP
 *
 */
@Repository
public interface VaccineHospitalDao extends CrudRepository<VaccineHospital, Integer> {

	public VaccineHospital findByHidAndVid(Hospital hospital, Vaccine vaccine);
}
