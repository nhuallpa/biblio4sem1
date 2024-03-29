package com.library

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext


class BookController {
	
	
	static TOP_BOOKS = 6;
	def bookService
	def geocoderService
	def scaffold = true
	def nearBook
	

	
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
		println params
		
		if(param_q != ""){

			def searchResults = Book.search(param_q, params)
			
			if(params.categorias == "Nearly"){
				
				println searchResults
				def someList = new ArrayList()

				User user = session.user
				
				
				
				searchResults.results.each{
					def index = it
					def librarysAvailables = bookService.getLibraryAvailable(it)
					librarysAvailables.each {
						// Solo muestra los libos cercanos en la busqueda, entiendase cercanos
						// como menor distancia a HugeVal
						def hugeVal = 999
						
						def latLng = geocoderService.geocodeLocation(it)
						def lat1 = latLng.result.geometry.location.lat
						def lng1 = latLng.result.geometry.location.lng 
						def lat = lat1.toDouble()
						def lng = lng1.toDouble()
						
						def userLocation = geocoderService.geocodeLocation(user)
						def latUser1= userLocation.result.geometry.location.lat 
						def lngUser1 = userLocation.result.geometry.location.lng
						def latUser = latUser1.toDouble()
						def lngUser = lngUser1.toDouble()
						
						double latDef = lat
						latDef = latDef - latUser
						latDef = latDef * latDef
						double lngDef = lng
						lngDef =  lngDef - lngUser
						lngDef = lngDef * lngDef
						double distance = Math.sqrt(lngDef + latDef)
						println distance
						if ( distance < hugeVal){
							nearBook = index
							hugeVal = distance
						}								
					}	
					someList << nearBook
				}
							
				return [searchResults:someList, resultCount:someList.size]
				
			}
			
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
		
		def librarysAvailables = bookService.getLibraryAvailable(aBook)
		
		[book : aBook, librarys:librarysAvailables]
		
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
	
	def updateTags = {
		Book book = Book.get(params.idBook)
		String tags = params["book-tags-area"]
		if (book && tags) {
			book.updateTags(tags)
			
		}
		redirect(controller:'book', action:'viewDetails', params:[bookId:params.idBook])
	}
	
	/** MOBILE **/
	
	def getLibrarys = {
		Book aBook = Book.get(params.bookId)
		def librarysData = bookService.getLibraryAvailable(aBook)
		def librarys = new ArrayList()
		for(obj in librarysData){
			def library = [
					id: obj.id,
					libraryId: obj.libraryId,
					name:obj.name,
					phone: obj.phone,
					email: obj.email
				]
			librarys.add library
		}
		render librarys as JSON
	}
	
	def getTopBooks = {
		def books = bookService.getInTop()
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
//				comments: [
//						obj.comments
//					],
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
		def jsonData = [
				isbn: bookFounded.ISBN,
				id: bookFounded.id,
				state: bookFounded.state.state,
				name: bookFounded.name,
				description: bookFounded.description,
				author: bookFounded.author,
			]
		
		render jsonData as JSON
	}
	
	/**
	 * Devuelve en JSON las librerias donde se encuentra el libro
	 * @param aBook
	 * @return list of librarys
	 */
	List<Library> getLibrarys(Book aBook){
		def librarysData = bookService.getLibraryAvailable(aBook)
		def librarys = new ArrayList()
		for(obj in librarysData){
			def library = [
					id: obj.libraryId,
					name:obj.name
				]
			librarys.add library
		}
		librarys
	}
	
	def searchBook = {
		
		JSONObject jsonObject = request.JSON
		
		def param_q = jsonObject.getString("q")
		def searchResults = Book.search(param_q, params)
		
		List<Book> resultList = searchResults.results
		def jsonList = new ArrayList()
		
		if(searchResults.total > 0){
			for(obj in resultList){
				def librarys = getLibrarys(obj)
				def jsonData = [
					id:obj.id,
					isbn: obj.ISBN,
					state: obj.state.state,
					name: obj.name,
					description: obj.description,
					author: obj.author,
					librarys: librarys ,
					commentsSize: obj.comments.size()
				]
				jsonList.add jsonData
			}
		}

		render jsonList as JSON
	}
	
	
	def getPicture = {
	
		JSONObject jsonObject = request.JSON
		def bookName = jsonObject.getString("name")
		def location = new File("web-app/images/Book/" + bookName + "/cover.jpg")
		response.setContentType("application/jpg")
		response.setContentLength(location.size().toInteger())
		OutputStream out = response.getOutputStream();
		out.write(location.bytes);
		out.close();
	
	}
	
	
	
	void goToHome(){
		redirect(uri: '/')
	}
}
