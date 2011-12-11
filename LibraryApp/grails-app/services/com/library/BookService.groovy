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
	
	
	/*
	 * Devuelve las librerias que tengan ejemplares disponibles de aBook
	 * */
	def getLibraryAvailable(Book aBook) {
		
		def l = Library.createCriteria()
		def result = l.list {
			projections {
				distinct('id')
			}
			bookCopys{
				eq("bookMaster",aBook)
			}
		}
		return Library.getAll(result);
	}
	
	/**
	 * Devuelve recomendaciones de libros basandose en las etiquetas. No rankea por el momento.
	 * */
	def getRecommendation(List tags) {
	
		def books
		if (tags) {
			String findByTagHQL = """
			SELECT DISTINCT book
			FROM Book book, TagLink tagLink
			WHERE book.id = tagLink.tagRef
			AND tagLink.tag.name IN (:tags)
			"""
			
			books = Book.executeQuery(findByTagHQL, [tags:tags], [max:6] )
		}
		return books
	}
}
