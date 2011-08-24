package com.library.android.dto;


public class Library {
	
	private String libraryId;
	private String name;
//	byte[] photo
	private String homepage;
	private String email;
	private String phone;	
	private Location location;
	
	public Library(String libraryId, String name){
		this.libraryId = libraryId;
		this.name = name;
	}

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
	

}
