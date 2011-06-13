package com.library

class BookController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	def view = {
		[bookList : Book.list()]
	}
	def searchBookHome = {
	}
	def searchBookByTitle = {
		def books = Book.list()
		redirect(controller:'book', action:'searchBookHome', bookList:books)
	}
}
