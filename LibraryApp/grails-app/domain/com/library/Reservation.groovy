package com.library

class Reservation {
	
	static searchable = true
	
	Book book
	User user
	Date reservationDate
	String state
	Library library
    static constraints = {
		// book(unique: true)
		state(size: 3..60)
		// user(nullable: true)
		reservationDate(nullable:true)
		library(nullable:true)
		
    }
	
	static belongsTo = [user:User, library:Library]
	Reservation(){
		book = new Book()
		user = new User()
		
	}
	
	Reservation(Book aBook, User aUser){
		this.setBook(aBook)
		this.user = aUser
		this.state = "Reserved"
		aBook.reserveMe()
		// this.library = aBook?.library
	}
	
	Boolean isReserved() {
		return state.contains("Reserved")
	}
	
	Boolean isDelivereded() {
		return state.contains("Delivered")
    }
	
}
