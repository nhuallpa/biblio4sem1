package com.library

import grails.converters.JSON

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
	
	/** MOBILE **/
	
	def getLibrary = {
		Library library = Library.get(params.libId)
		def jsonData = [
				name: library.name,
				homepage: library.homepage,
				email: library.email,
				phone: library.phone,
				location: [
						street: library.location.street,
						city: library.location.city,
						country: library.location.country,
					],
			]	
		
		render jsonData as JSON
		
	}

	
}
