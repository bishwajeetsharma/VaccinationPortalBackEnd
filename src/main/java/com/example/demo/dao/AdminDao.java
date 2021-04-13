package com.example.demo.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admin;
import com.example.demo.model.Auth;

@Repository
public interface AdminDao extends CrudRepository<Admin, Integer>{

	public Admin findByAuth(Auth auth);
}
