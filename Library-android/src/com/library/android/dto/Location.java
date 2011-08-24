package com.library.android.dto;

public class Location {
	
	private String address;
	private String city;
	private String country;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
