package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceExistsException;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.LocationDao;
import com.example.demo.dao.RolesDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Auth;
import com.example.demo.model.Location;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.model.UserRegistration;

@Service
@Transactional
public class UserRegistrationService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private AuthDao authdao;
	@Autowired
	private LocationDao locationdao;
	@Autowired
	private RolesDao rolesdao;

	public UserRegistrationService() {
		super();
	}

	public void registerUser(UserRegistration user_reg) {

		User user = user_reg.getUser();
		Location location_user = user_reg.getLocation();
		Auth auth = user_reg.getAuth();

		if (userdao.existsByAadhar(user.getAadhar()))
			throw new ResourceExistsException("UID already exists");

		if (userdao.existsByContactno(user.getContactno()))
			throw new ResourceExistsException("Contact number already exists");

		if (authdao.existsByPassword(auth.getPassword()))
			throw new ResourceExistsException("Password already exists");

		if (authdao.existsByUsername(auth.getUsername()))
			throw new ResourceExistsException("Username already exists");

		Roles role = rolesdao.findByRolename("user");
		auth.setRole(role);
		auth.setEnable("true");
		authdao.save(auth);
		Location location_persisted = locationdao.findByCityAndState(location_user.getCity(), location_user.getState());
		if (location_persisted == null) {
			locationdao.save(location_user);
			user.setLocation(location_user);
		} else {
			user.setLocation(location_persisted);
		}
		user.setAuth(auth);
		userdao.save(user);

	}
}
