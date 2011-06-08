package com.library

class Reservation {
	
	Book book
	User user
	Date reservationDate
	String state
	Library library
	

    static constraints = {
		book(unique: true,nullable:true)
		state(size: 3..60)
		user(unique:true)
		reservationDate(nullable:true)
		library(unique:true)
		
    }
	
	static belongsTo = [User]
	
	Reservation(Book aBook, User aUser){
		this.setBook(aBook)
		this.user = aUser
		this.state = "Reserved - Waiting"
		this.library = aBook?.library
	}
	
	Boolean isReserved(String comment, String User) {
		if ( state == "Reserved - Waiting") return true
		return false
	}
	
	Boolean isDelivereded(String comment, String User) {
		if ( state == "Delivered") return true
		return false
    }
	
}
