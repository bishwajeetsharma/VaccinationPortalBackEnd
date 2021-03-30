package com.example.demo.dao;





import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Auth;
@Repository
public interface AuthDao extends CrudRepository<Auth, Integer>{

	public boolean existsByUsername(String username);
	public  boolean existsByPassword(String password);
}
