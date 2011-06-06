package com.library.android.domain;

public class Location {
	
	private String address;
	private float x;
	private float y;
	
	public Location(String address){
		this.address = address;
		this.x = 0;
		this.y = 0;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	

}
