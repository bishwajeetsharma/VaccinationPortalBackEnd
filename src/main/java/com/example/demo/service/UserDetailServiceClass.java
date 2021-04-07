package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthDao;
import com.example.demo.model.Auth;
import com.example.demo.utils.UserDetailsClass;

@Service
public class UserDetailServiceClass implements UserDetailsService {

	@Autowired
	private AuthDao authdao;

	@Override
	public UserDetailsClass loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Auth> auth = authdao.findByUsername(username);
		auth.orElseThrow(() -> (new UsernameNotFoundException("Username doesn't exist")));
		return auth.map(UserDetailsClass::new).get();
	}

}
