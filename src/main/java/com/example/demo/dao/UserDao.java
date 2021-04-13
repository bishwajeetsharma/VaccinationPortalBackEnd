package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Auth;
import com.example.demo.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{

	public boolean existsByContactno(String contactno);
	public boolean existsByAadhar(String aadhar);
	public Optional<User> findByAuth(Optional<Auth> authDetails);
}
