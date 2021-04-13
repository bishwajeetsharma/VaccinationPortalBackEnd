package com.example.demo.utils;

import java.util.ArrayList;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Auth;

public class UserDetailsClass implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private int id;
	private boolean isEnabled;
	private String role;
	private ArrayList<GrantedAuthority> authorities;

	public UserDetailsClass(Auth auth,String firstname,String lastname,Integer id,String role) {
		this.username = auth.getUsername();
		this.password = auth.getPassword();
		this.firstname=firstname;
		this.lastname=lastname;
		this.id=id.intValue();
		this.role=role;
		this.isEnabled = (auth.getEnable().equals("true")) ? true : false;
		this.authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(auth.getRole().getRolename()));
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return isEnabled;
	}

}
