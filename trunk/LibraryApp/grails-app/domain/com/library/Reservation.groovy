package com.library

import grails.converters.JSON

/**
 * Si se elimina un reservation, el ejemplar debe volver a estar disponible en la libreria
 * */
class Reservation {
	
	static searchable = true
	
	BookCopy bookCopy
	User user
	Date reservationDate
	Date reservationExpirationDate
	Date deliverDate
	Date deliverExpirationDate
	
	States state
	Library library
    static constraints = {
		state(inList:States.list())
		reservationDate(nullable:true)
		library(nullable:true)
    }
	
	static mapping = {
		bookCopy cascade:"save-update"
	}
	
	static belongsTo = [user:User, library:Library]
	

	Reservation(){
		bookCopy = new BookCopy()
		user = new User()
	}
	
	Reservation(BookCopy aBookCopy, User aUser){
		this.setBookCopy(aBookCopy)
		this.user = aUser
		this.state = States.RESERVED
		aBookCopy.reserveMe()
	}
	
	Boolean isReserved() {
		return bookCopy.isReserved()
	}
	
	Boolean isDelivered() {
		return bookCopy.isDelivered()
    }
	
	
	
	String nameOfBook() {
		String name
		if (bookCopy) {
			name = bookCopy.bookMaster.name 
		}
		return name
	}
	
	States stateOfBook() {
		States state
		if (bookCopy) {
			state = bookCopy.state
		}
		return state
	}
	/**
	 * After a reservation, a user has to take away the book reserved
	 * */
	void deliverBook() {
		bookCopy?.deliver();
	}
}
