package com.example.demo.model;

public class ChatPartcipant {

	private int id;
	private String fullname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public ChatPartcipant(int id, String fullname) {
		super();
		this.id = id;
		this.fullname = fullname;
	}
}
