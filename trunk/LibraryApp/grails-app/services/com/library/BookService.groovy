package com.library

class BookService {

    static transactional = true

    def getInTop() {
		def number = 6;
		
		def criteria = Book.createCriteria()
		def results = criteria.list {
			order("rating", "desc")
			maxResults(number)
		}
		results
    }
}
