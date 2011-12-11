package com.library

import org.codehaus.groovy.grails.web.json.JSONObject;

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
		JSONObject json = request.JSON
		User user = User.get(json.getString("userId"))
		Book aBook = Book.get(json.getString("bookId"))
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
		User userFound = User.get(user.id)
		if(!bookAvailable(aBook)){
			aMessage = "${aBook.name} is not available"
		} else {
			userFound.makeReservation(aBook, library)
			aMessage = "You have reserved ${aBook.name}!"
		} 
		flash.message = aMessage

		redirect(controller:'user', action: 'viewProfile', params:[userId:userFound.id])
	}
	
	
	def cancelReserve = {
		User user = session.user
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){
			def userFound = User.get(user.id)
			userFound.cancelReservation aBook  
			flash.message = "You have canceled the reservation ${aBook.name}"
			redirect(controller:'user', action: 'viewProfile', params:[userId:userFound.id])
		}
	}
	
	def deliverBook = {
		User user = session.user
		Reservation reservation = Reservation.get(params.reservationId)
		if (reservation.isReserved())  {
			reservation.deliverBook();
		}
		redirect(controller:'user', action: 'viewProfile', params:[userId:user.id])
	}
	
	def returnBook = {
		User user = session.user
		BookCopy aBookCopy = BookCopy.get(params.bookCopyId)
		if (aBookCopy.isDelivered())  {
			def userFound = User.get(user.id)
			userFound.returnBook aBookCopy
		}
		redirect(controller:'user', action: 'viewProfile', params:[userId:user.id])
	}

	
//  TODO: a borrar
//	boolean bookAvailable(Book book){
//		return book.state == 'Available'
//		return true;
//	}
	
	void goToHome(){
		redirect(uri: '/')
	}
}
