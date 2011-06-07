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
    }
	
	static belongsTo = [User]
	
	void isReserved(String comment, String User) {
		
	}
	
	void isDelivereded(String comment, String User) {
	
    }
	
	Reservation(Book aBook, User aUser){
		this.book = aBook
		this.user = aUser
		//this.library = book.library
		this.state = "Reserved Waiting"
	}

}
