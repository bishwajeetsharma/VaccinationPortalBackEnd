package com.example.demo.Exception;

import java.util.Date;

public class Details {

	public Details() {
		super();
	}

	public Details(Date datestamp, String message, String requesturl) {
		super();
		this.datestamp = datestamp;
		this.message = message;
		this.requesturl = requesturl;
	}

	private Date datestamp;
	private String message;
	private String requesturl;

	public Date getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}
}
