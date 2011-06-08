package com.library

class UserController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
}
