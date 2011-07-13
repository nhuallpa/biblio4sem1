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
		if (!user.isAttached()) {
			user.attach()
			listOfMyReservations = user.reservations
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
					aMessage = "${aBook.name} reserved!"
				} 
		flash.message = aMessage
		redirect(action: 'viewMyReservation')
	}
	
	boolean bookAvailable(Book book){
		return book.state.state == 'Available'
	}
}
