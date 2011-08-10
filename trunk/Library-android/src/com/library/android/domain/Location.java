package com.library.android.domain;

public class Location {
	
	private String address;
	private double x;
	private double y;
	
	public Location(String address){
		this.address = address;
		this.x = 0;
		this.y = 0;
	}
	
	public Location(String address, double latitude, double longitude){
		this.address = address;
		this.x = latitude;
		this.y = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	

}
