package com.library

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
}
