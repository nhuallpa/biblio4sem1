package com.library

class AwardController {

	def scaffold = true
	
	def index = {
		redirect(action: 'create')
	}
	
	def awards = {
		[awards : Award.list()]
	}
	
}
