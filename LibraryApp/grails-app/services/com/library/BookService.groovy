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
	
	def getRecommendation(List tags) {
	
		String findByTagHQL = """
		SELECT DISTINCT book
		FROM Book book, TagLink tagLink
		WHERE book.id = tagLink.tagRef
		AND tagLink.tag.name IN (:tags)
		"""
		
		Book.executeQuery(findByTagHQL, [tags:tags], [max:6] )
	}
}
