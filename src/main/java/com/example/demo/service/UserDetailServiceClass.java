package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Admin;
import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import com.example.demo.utils.UserDetailsClass;

@Service
public class UserDetailServiceClass implements UserDetailsService {

	@Autowired
	private AuthDao authdao;

	@Autowired
	private UserDao userdao;

	@Autowired
	private DoctorDao doctordao;

	@Autowired
	private AdminDao admindao;

	@Override
	public UserDetailsClass loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Auth> auth = authdao.findByUsername(username);
		auth.orElseThrow(() -> (new UsernameNotFoundException("Username doesn't exist")));
		Auth authObj = auth.get();

		String role = authObj.getRole().getRolename();
		switch (role) {
		case "doctor":
			Doctor doctor = doctordao.findByAuth(authObj);
			return new UserDetailsClass(authObj, doctor.getFirstname(), doctor.getLastname(), doctor.getAuth().getId(),"doctor");
		case "user":
			User user = userdao.findByAuth(authObj);
			return new UserDetailsClass(authObj, user.getFirstname(), user.getLastname(), user.getAuth().getId(),"user");
		default:
			Admin admin = admindao.findByAuth(authObj);
			return new UserDetailsClass(authObj, admin.getFirstname(), admin.getLastname(), admin.getAuth().getId(),"admin");
		}

	}

}
