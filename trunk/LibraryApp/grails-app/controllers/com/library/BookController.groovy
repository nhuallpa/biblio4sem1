package com.library

import grails.converters.JSON

import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext


class BookController {
	
	static TOP_BOOKS = 6;
	def bookService
	def scaffold = true
	
	def getTopBooks = {
		def books = Book.list()
		def result = new ArrayList()
		for(int i = 0; i < TOP_BOOKS; i++){
			Book obj = books.get(i)
			def jsonBook = [
				
				id:obj.id,
				isbn: obj.ISBN,
				state: obj.state.state,
				name: obj.name,
				description: obj.description,
				author: obj.author,
				comments: [
						obj.comments
					]
				]
			
			result.add jsonBook
		}
		
		render result as JSON
	}
	
	
	def getBookComments = {
		def book = Book.get(params.bookId)
		def comments = new ArrayList()
		for(obj in book.comments){
			def jsonData = [
					id: obj.id,
					description : obj.description,
					date: obj.date,
					user: [
							id:obj.sourceUser.id,
							name: obj.sourceUser.name
						],
					book: [
							id:obj.book.id,
							name: obj.book.name,
							description: obj.book.description
						],
					score: obj.score
				]
			comments.add jsonData
		}
		
		render comments as JSON
	}
	
	def getBook = {
		
		def bookFounded = Book.get(params.bookId)
//		def comments = render (controller:'book', action:'getBookComments', bookId: bookFounded.id)
		def jsonData = [
			isbn: bookFounded.ISBN,
			state: bookFounded.state.state,
			name: bookFounded.name,
			description: bookFounded.description,
			author: bookFounded.author,
//			comments: comments,
			] as JSON
		
		render jsonData
	}
	
    def index = { 
		def results = bookService.getInTop()
		def books = Book.list()
		[booksInTopFive:results, listOfBooks:books]
	}
	def view = {
		[bookList : Book.list()]
	}
	def searchBookHome = {
	}
	def search = {

		def itemPerPage = 9
		def param_q = params.q
		params.max = itemPerPage
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
		flash.message = null
		if(aBook.comments.size() == 0){
			flash.message = "${aBook.name} does not has comments!"
		}
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
	
	/**
	 * return five books with maximun rating
	 * */
	def getTopFive = {
		def results = bookService.getTopFive()
		[booksInTopFive:results]
	}
}
