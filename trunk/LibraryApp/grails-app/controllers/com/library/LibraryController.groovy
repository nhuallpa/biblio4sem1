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
	
	def getAllLibrarys = {
		List<Library> librarys = Library.list()
		def libraryList = new ArrayList()
		for(obj in librarys){
			def jsonData = [
					id: obj.id,
					name: obj.name,
					location: [
						street: obj.location.street,
						city: obj.location.city,
						country: obj.location.country,
					]
				]
			libraryList.add jsonData
		}
		
		render libraryList as JSON
		
	}
	
}
