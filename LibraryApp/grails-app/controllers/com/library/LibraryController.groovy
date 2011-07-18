package com.library

class LibraryController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	
	def view = {
		[libraryList : Library.list()]
	}
	
	def gmaps = {
		[libraryList : Library.list()]
	}
	
}
