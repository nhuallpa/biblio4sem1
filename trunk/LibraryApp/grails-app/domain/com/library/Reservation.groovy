package com.library


class Reservation {
	
	static searchable = true
	
	Book book
	User user
	Date reservationDate
	States state
	Library library
    static constraints = {
		state(inList:States.list())
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
		this.state = States.RESERVED
		aBook.reserveMe()
		// this.library = aBook?.library
	}
	
	Boolean isReserved() {
		return (state == States.RESERVED)
	}
	
	Boolean isDelivereded() {
		return ( state == States.DELIVERED)
    }
	
}
