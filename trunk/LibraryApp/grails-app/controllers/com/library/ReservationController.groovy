package com.library

import grails.converters.JSON

class ReservationController {

	def scaffold = true
	
	//mobile
	def getReservation = {
		def reservation = Reservation.get(params.reservationId)
		render reservation as JSON
		
	}
	
	//mobile
	def reserveBook = {
		User user = User.get(params.userId)
		Book book = Book.get(params.bookId)
		Library library = Library.get(params.libraryId)
		user.makeReservation(book, library)
		response.writer.println("Reservation: " + book.name + " by " + user.name)
	}
	
	def MOBcancelReserve = {
		User user = User.get(params.userId)
		Book aBook = Book.get(params.bookId)
		def userFound = User.get(user.id)
		userFound.cancelReservation aBook
		response.writer.println("Reservation canceled")
	}
	
	/***************************************************/
	
    def index = { 
		redirect(action: 'create')
	}
	
	def reserveRequest = {
		
	}
	
	
	def toReserve = {
		User user = session.user
		if (!user){
			goToHome()
		}
		def aMessage = null
		
		Book aBook = Book.get(params.bookId)
		Library library = Library.get(params.libraryId)
		if(!bookAvailable(aBook)){
			aMessage = "${aBook.name} is not available"
		} else if (!user.isAttached() && aBook) {
					user.attach()
					user.makeReservation(aBook, library)
					aMessage = "You have reserved ${aBook.name}!"
				} 
		flash.message = aMessage

		redirect(controller:'book', action: 'viewDetails', params:[bookId:aBook.id])
	}
	
	
	def cancelReserve = {
		User user = session.user
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){
			user.attach();
			def userFound = User.get(user.id)
			userFound.cancelReservation aBook  
			flash.message = "You have canceled the reservation ${aBook.name}"
			redirect(controller:'user', action: 'viewProfile', params:[userId:userFound.id])
		}
	}
	
	boolean bookAvailable(Book book){
//		return book.state == 'Available'
		return true;
	}
	
	void goToHome(){
		redirect(uri: '/')
	}
}
