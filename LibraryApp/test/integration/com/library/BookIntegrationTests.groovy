package com.library

import org.apache.tools.ant.types.Description;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import grails.test.*

class BookIntegrationTests extends GroovyTestCase {
	
	String myname
	long myISBN
	Book book
	
	Library libraryDefault;
	
    protected void setUp() {
        super.setUp()
		myname = 'Thinking in Java'
		myISBN = 12345
		book = new Book(ISBN : myISBN, name : myname, state: States.AVAILABLE)
		
		
		libraryDefault = new Library(libraryId:'BS_ATENEO', name:'Ateneo')
		libraryDefault.save()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNewBook() {
		
		libraryDefault.addToBooks(book)
		assertTrue book.validate()
		assertNotNull book.save()
		assertNotNull book.id

		def foundBook = Book.get(book.id)
		assertEquals myname,foundBook.getName()
    }
	
	void testTopFive() {
		Book book1 = new Book(ISBN : 12341, name : 'book1', state: States.AVAILABLE)
		Book book2 = new Book(ISBN : 12342, name : 'book2', state: States.AVAILABLE)
		Book book3 = new Book(ISBN : 12343, name : 'book3', state: States.AVAILABLE)
		Book book4 = new Book(ISBN : 12344, name : 'book4', state: States.AVAILABLE)
		Book book5 = new Book(ISBN : 12345, name : 'book5', state: States.AVAILABLE)
		Book book6 = new Book(ISBN : 12346, name : 'book6', state: States.AVAILABLE)
		Book book7 = new Book(ISBN : 12347, name : 'book7', state: States.AVAILABLE)
		Book book8 = new Book(ISBN : 12348, name : 'book8', state: States.AVAILABLE)
		Book book9 = new Book(ISBN : 12349, name : 'book9', state: States.AVAILABLE)
		
		libraryDefault.addToBooks(book1)
		libraryDefault.addToBooks(book2)
		libraryDefault.addToBooks(book3)
		libraryDefault.addToBooks(book4)
		libraryDefault.addToBooks(book5)
		libraryDefault.addToBooks(book6)
		libraryDefault.addToBooks(book7)
		libraryDefault.addToBooks(book8)
		libraryDefault.addToBooks(book9)
		
		
		User user = new User()
		user.save()
		user.addBookComment book1, "Genial1", 1
		user.addBookComment book2, "Genial2", 2
		user.addBookComment book3, "Genial3", 3
		user.addBookComment book4, "Genial4", 4
		user.addBookComment book5, "Genial5", 5
		user.addBookComment book6, "Genial6", 6
		user.addBookComment book7, "Genial7", 7
		user.addBookComment book8, "Genial8", 8
		user.addBookComment book9, "Genial9", 9
		
		
		user.save()
		
		def criteria = Library.createCriteria()
		def results = criteria {		
			createAlias "books", "b"
			projections {
				groupProperty("b.name")
				max("b.totalVotes")
			}
			maxResults(5)
		}
		
		def resultMap = results.inject([:]) { map, book ->
			map[book[0]] = book[1]; map
		} 
	
	}
}
