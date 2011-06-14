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
	def search = {
		//render Book.search(params.q, params)
		def searchResults = Book.search(params.q, params)
		flash.message = "${searchResults.total} results found for search: ${params.q}"
		flash.q = params.q
		return [searchResults:searchResults.results, resultCount:searchResults.total]
	}
	
	def searchBookByTitle = {
		def books = Book.list()
		redirect(controller:'book', action:'searchBookHome', bookList:books)
	}
}
