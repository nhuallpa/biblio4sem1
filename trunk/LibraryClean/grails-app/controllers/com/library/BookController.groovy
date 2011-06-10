package com.library

class BookController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	
	def view = {
		[BookList : Book.list()]
	}
}
