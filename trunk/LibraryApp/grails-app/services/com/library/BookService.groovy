package com.library

class BookService {

    static transactional = true

    def getTopFive() {
		def five = 5;
		
		def criteria = Book.createCriteria()
		def results = criteria.list {
			order("rating", "desc")
			maxResults(five)
		}
		results
    }
}
