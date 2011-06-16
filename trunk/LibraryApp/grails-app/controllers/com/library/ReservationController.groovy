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
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached() && aBook) {
			user.attach()
			user.makeReservation(aBook)
		}
		redirect(action: 'viewMyReservation')
	}
}
