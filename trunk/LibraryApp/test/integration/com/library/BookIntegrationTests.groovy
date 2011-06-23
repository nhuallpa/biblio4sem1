package com.library

import org.apache.tools.ant.types.Description;

import grails.test.*

class BookIntegrationTests extends GroovyTestCase {
	
	String myname
	long myISBN
	Book book
	String commentDesc
	
    protected void setUp() {
        super.setUp()
		myname = 'Thinking in Java'
		myISBN = 12345
		book = new Book(ISBN : myISBN, name : myname, state: States.AVAILABLE)
		commentDesc = 'Buen libro'
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
	
//	void testAddCommentToBook(){
////		Comment comment = new Comment(description : 'Buen libro')
//		
//		Location locationUser = new Location (country: "countryTest", street: "streetTest")
//		User user = new User(name : 'Gonzalo', location : locationUser)
//		assertNotNull user.save()
//		book.addComment user, commentDesc, 15
//		assertNotNull book.save()
//		
//		Book foundBook = Book.get(book.id) 
//		assertEquals 1,foundBook.comments?.size()
////		assertEquals 'Gonzalo', foundBook.getComments().get(0)
//		
//	}
}
