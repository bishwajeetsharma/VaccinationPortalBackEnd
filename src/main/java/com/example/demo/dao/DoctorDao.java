package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.Location;

@Repository
public interface DoctorDao extends CrudRepository<Doctor, Integer>{

	public Doctor findByAuth(Auth auth);
	public Iterable<Doctor>findAll();
	public boolean existsByRegNo(String regNo);
	public boolean existsByContactno(String contactno);
	public Optional<List<Doctor>> findAllByLocation(Location location);
	public Doctor findByRegNo(String regNo);}
