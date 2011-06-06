package com.library

class LocationController {

	def scaffold = true
	
    def index = {
		redirect(action: 'create')
	}
}
