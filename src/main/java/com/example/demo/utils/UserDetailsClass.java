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
	private boolean isEnabled;
	private ArrayList<GrantedAuthority> authorities;

	public UserDetailsClass(Auth auth) {
		this.username = auth.getUsername();
		this.password = auth.getPassword();
		this.isEnabled = (auth.getEnable().equals("true")) ? true : false;
		this.authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(auth.getRole().getRolename()));
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
