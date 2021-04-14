package com.example.demo.dao;





import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Auth;
@Repository
public interface AuthDao extends CrudRepository<Auth, Integer>{

	public static final String FIND_IDS="SELECT id from auth";
	public boolean existsByUsername(String username);
	public  boolean existsByPassword(String password);
	public Optional<Auth> findByUsername(String username);
	public Auth findByPassword(String password);
	@Query(value=FIND_IDS,nativeQuery = true)
	public Iterable<Integer> findAllId();
}
