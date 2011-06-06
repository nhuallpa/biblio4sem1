package com.library

class Library {

	String libraryId
	String name
	byte[] photo
	String homepage
	String email
	String phone
	
	Location location

    static constraints = {
		libraryId(size: 3..20, unique: true)
		name(size: 3..60)
		photo(nullable: true)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		phone(nullable: true)
		location(nullable: true)
    }
	
	static mapping = {
		location lazy: false
	}
	
	void toComment(String comment, String User) {
		
	}
	
	Boolean isNearOf(Location location, Integer maxDistanceKm) {
		return false;
	}
}
