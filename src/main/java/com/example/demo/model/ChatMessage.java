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
	private Long fromId;
	private Long toId;
	private String message;
	private Long date;
	public Long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(long toId) {
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
	public ChatMessage(Long fromId, Long toId, String message, Long date) {
		super();
		this.fromId = fromId;
		this.toId = toId;
		this.message = message;
		this.date = date;
	}
	
	
}
