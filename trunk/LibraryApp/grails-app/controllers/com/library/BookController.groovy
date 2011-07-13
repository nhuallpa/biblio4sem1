package com.library

import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;
import org.springframework.context.ApplicationContext;

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
		def param_q = params.q
		
		if(param_q != ""){
			def searchResults = Book.search(param_q, params)
			flash.message = "${searchResults.total} results found for search: ${param_q}"
			flash.q = param_q
			return [searchResults:searchResults.results, resultCount:searchResults.total]
		} else {
			redirect(uri:'/')
		}
		
	}
	
	def searchBookByTitle = {
		def books = Book.list()
		redirect(controller:'book', action:'searchBookHome', bookList:books)
	}
	
	def searchAJAX = {
		def books = Book.findAllByNameIlike("%${params.query}%")
	
		//Create XML response
		render(contentType: "text/xml") {
		results() {
			books.each { book ->
			result(){
				name(book.name)
						//Optional id which will be available in onItemSelect
						id(book.id)
			}
		}
			}
		}
	}
	
	def rate = {
		
		Book aBook = Book.get(params.id)
		def rating = params.rating
		
		def average = (rating.toDouble() +
			aBook.rating*aBook.totalVotes)/
			(aBook.totalVotes + 1)
		aBook.rating = average
		aBook.totalVotes += 1
		aBook.save()
				
		render(template: "/book/rate",
		model: [book: aBook, rating: average])
		
	}
	
	def viewDetails = {
		Book aBook = Book.get(params.bookId)
		
		[book : aBook]
		
	}

	/**
	 * Action that upload a image Cover and save in FileSystem
	 * */
	def upload = {
		Book book = Book.get(params.id)
		def cover = request.getFile("cover")
		
		if (book && !cover.isEmpty()) {
			
			ApplicationContext applicationContext = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
			String pathFile = applicationContext.getResource('images/Book/').getFile().toString() + File.separatorChar + book.name +  File.separatorChar
			new File(pathFile).mkdirs()
			
			String contentType = cover.getContentType()
			
			cover.transferTo(
				new File("${pathFile}/cover.jpg")
			)
		}
		redirect(action:'loadImageCover')
	}
	
	/**
	 * This is for upload cover by form
	 * */
	def loadImageCover = {
		[listOfBook: Book.list()]
	}
}
