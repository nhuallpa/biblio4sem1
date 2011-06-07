package com.library

class ReservationController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
}
