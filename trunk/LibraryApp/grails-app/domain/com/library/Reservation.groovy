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
	Date returnDate;
	
	States state
	Library library
    static constraints = {
		state(inList:States.list())
		reservationDate(nullable:true)
		reservationExpirationDate(nullable:true)
		deliverDate(nullable:true)
		deliverExpirationDate(nullable:true)
		returnDate(nullable:true)
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
		reservationDate = new Date()
		reservationExpirationDate = reservationDate + Constants.RESERVATION_DAYS
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
			name = bookCopy.nameOfBook()
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
		deliverDate = new Date()
		deliverExpirationDate = deliverDate + Constants.LAUD_DAYS
		bookCopy?.deliver()
	}
	
	void returnBook() {
		returnDate = new Date()
		bookCopy?.returnMe()
	}
	
	Boolean good() { 
		Boolean finishedProperly = true
		def nowDate = new Date()
		if (nowDate > reservationExpirationDate) {
			finishedProperly = false
		} else if (returnDate > deliverExpirationDate) {
			finishedProperly = false
		}
		finishedProperly
	}
	
	void cancel() {
		bookCopy.cancelReservation()
	}
}
