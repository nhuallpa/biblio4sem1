package com.library

import org.apache.tools.ant.types.Description;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import grails.test.*

class BookIntegrationTests extends GroovyTestCase {
	
	def bookServives
	
	String myname
	long myISBN
	Book book
	
    protected void setUp() {
        super.setUp()
		myname = 'Thinking in Java'
		myISBN = 12345
		book = new Book(ISBN : myISBN, name : myname, state: States.AVAILABLE)
		
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNewBook() {
		
		assertTrue book.validate()
		assertNotNull book.save()
		assertNotNull book.id

		def foundBook = Book.get(book.id)
		assertEquals myname,foundBook.getName()
    }
	
	void testTopFive() {
		
		def five = 5;
		
		Book book1 = new Book(ISBN : 12341, name : 'book1', state: States.AVAILABLE).save()
		Book book2 = new Book(ISBN : 12342, name : 'book2', state: States.AVAILABLE).save()
		Book book3 = new Book(ISBN : 12343, name : 'book3', state: States.AVAILABLE).save()
		Book book4 = new Book(ISBN : 12344, name : 'book4', state: States.AVAILABLE).save()
		Book book5 = new Book(ISBN : 12345, name : 'book5', state: States.AVAILABLE).save()
		Book book6 = new Book(ISBN : 12346, name : 'book6', state: States.AVAILABLE).save()
		Book book7 = new Book(ISBN : 12347, name : 'book7', state: States.AVAILABLE).save()
		Book book8 = new Book(ISBN : 12348, name : 'book8', state: States.AVAILABLE).save()
		Book book9 = new Book(ISBN : 12349, name : 'book9', state: States.AVAILABLE).save()
		
		
		
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
		
		def criteria = Book.createCriteria()
		def results = criteria.list {
			order("rating", "desc")
			maxResults(five)
		}
		
		
		assertEquals five,  results.size()
		assertEquals 9, results[0].rating
		assertEquals 8, results[1].rating
		assertEquals 7, results[2].rating
		assertEquals 6, results[3].rating
		assertEquals 5, results[4].rating
	}
}
