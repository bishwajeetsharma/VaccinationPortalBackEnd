package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="role_name",nullable=false,unique = true)
	private String rolename;
	
	//one to many mapping with Auth
	@OneToMany(mappedBy = "role")
	private List<Auth> auth;
	
	//getters and setters
	public Roles() {}
	public List<Auth> getAuth() {
		return auth;
	}
	public void setAuth(List<Auth> auth) {
		this.auth = auth;
	}
	public Roles(String rolename) {
		super();
		this.rolename = rolename;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
