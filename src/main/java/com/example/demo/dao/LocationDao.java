package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Location;

@Repository
public interface LocationDao extends CrudRepository<Location, Integer> {

	public Location findByCityAndState(String city, String state);

	public Optional<Location> findByStateAndCity(String state, String city);
}
