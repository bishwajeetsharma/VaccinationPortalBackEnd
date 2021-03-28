package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Roles;

@Repository
public interface RolesDao extends CrudRepository<Roles, Integer>{

	public Roles findByRolename(String rolename);
}
