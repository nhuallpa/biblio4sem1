package com.library

import java.util.List;



class Library {

	String libraryId
	String name
//	byte[] photo
	String homepage
	String email
	String phone
	
	Location location

	static hasMany = [reservations : Reservation, books : Book]

    static constraints = {
		libraryId(size: 3..20, unique: true)
		name(size: 3..60)
//		photo(nullable: true)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		phone(nullable: true)
		location(nullable: true)
		reservations(nullable:true)
		books(nullable:true)
    }
	
	static mapping = {
		location lazy: false
	}
	
	void comment(String comment, String User) {
		
	}
	
	Boolean isNearOf(Location location, Integer maxDistanceKm) {
		return false;
	}
	
	String toString(){
		return this.name
	}
	
}
