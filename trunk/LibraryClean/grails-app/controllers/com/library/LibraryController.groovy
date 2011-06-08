package com.library

class LibraryController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
}
