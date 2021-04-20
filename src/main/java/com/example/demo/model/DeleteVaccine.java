package com.example.demo.model;

public class DeleteVaccine {

	private int vid;
	private int hid;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public DeleteVaccine(int vid, int hid) {
		super();
		this.vid = vid;
		this.hid = hid;
	}
}
