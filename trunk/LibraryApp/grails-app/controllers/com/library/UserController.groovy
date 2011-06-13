package com.library

class UserController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	
	def login = {
		
		User user = User.findByNameAndPassword(params.userId, params.password)
		if (user) {
			session.user = user
		} else {
			session.user = null
		}
		redirect(controller:'home', action:'index')
	}
	
	def logout = {
		session.user = null
		redirect(controller:'home', action:'index')
	}
}
