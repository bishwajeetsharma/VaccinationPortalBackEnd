package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Integer fromId;
	private Integer toId;
	private String message;
	private Long date;
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public ChatMessage() {}
	public ChatMessage(Integer fromId, Integer toId, String message, Long date) {
		this.fromId = fromId;
		this.toId = toId;
		this.message = message;
		this.date = date;
	}
	
	
}
