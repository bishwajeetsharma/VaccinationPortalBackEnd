package com.example.demo.model;

public class MessageRequest {

	private int from;
	private int to;

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public MessageRequest() {
	}

	public MessageRequest(int from, int to) {
		this.from = from;
		this.to = to;
	}
}
