package com.library

class Reservation {
	
	static searchable = true
	
	Book book
	User user
	Date reservationDate
	String state
	//Library library
	static belongTo = [library : Library]
	

    static constraints = {
		book(unique: true,nullable:true)
		state(size: 3..60)
		user(unique:true)
		reservationDate(nullable:true)
		// library(unique:true, nullable:true)
		
    }
	
	static belongsTo = [User]
	Reservation(){
		book = new Book()
		user = new User()
		
	}
	
	Reservation(Book aBook, User aUser){
		this.setBook(aBook)
		this.user = aUser
		this.state = "Reserved - Waiting"
		// this.library = aBook?.library
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
