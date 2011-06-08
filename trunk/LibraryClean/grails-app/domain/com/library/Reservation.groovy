package com.library

class Reservation {
	
	Book book
	User user
	Date reservationDate
	String state
	Library library
	

    static constraints = {
		book(unique: true)
		state(size: 3..15)
		user(unique:true)
		reservationDate(nullable:true)
		library(unique:true)
		
    }
	
	static belongsTo = [User]
	
	Boolean isReserved(String comment, String User) {
		if ( state == "Reserved - Waiting") return true
		return false
	}
	
	Boolean isDelivereded(String comment, String User) {
		if ( state == "Delivered") return true
		return false
    }
	
	Reservation(Book aBook, User aUser){
		this.book = aBook
		this.user = aUser
		//this.library = book.library
		this.state = "Reserved - Waiting"
	}

}
