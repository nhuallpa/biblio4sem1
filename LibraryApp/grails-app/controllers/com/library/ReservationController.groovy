package com.library

import com.sun.jndi.toolkit.url.Uri;

class ReservationController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	
	def viewMyReservation = {
		User user = session.user
		def listOfMyReservations = null
		if(!user){
			goToHome()
		}
		if (user) {
			User userFound = User.get(user.id) 
			listOfMyReservations = userFound.reservations
		}
		[reservations : listOfMyReservations]
	}
	
	def toReserve = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		def aMessage = null
		Book aBook = Book.get(params.bookId)
		if(!bookAvailable(aBook)){
			aMessage = "${aBook.name} is not available"
		} else if (!user.isAttached() && aBook) {
					user.attach()
					user.makeReservation(aBook)
					aMessage = "You are reserved ${aBook.name}!"
				} 
		flash.message = aMessage
		redirect(action: 'viewMyReservation')
	}
	
	def cancelReserve = {
		User user = session.user
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){
			user.attach();
			user.cancelReservation aBook   //--> @gonza: no cancela la reserva porque no la encuentra, pero esta creada...
			flash.message = "You canceled the reservation ${aBook.name}"
			redirect(action: 'viewMyReservation')
		}
	}
	
	boolean bookAvailable(Book book){
		return book.state.state == 'Available'
	}
	
	void goToHome(){
		redirect(uri: '/')
	}
}
