package com.library

import org.apache.tools.ant.types.Description;

import grails.test.*

class BookIntegrationTests extends GroovyTestCase {
	
	String myname
	long myISBN
	Book book
	String commentDesc
	
	Library libraryDefault;
	
    protected void setUp() {
        super.setUp()
		myname = 'Thinking in Java'
		myISBN = 12345
		book = new Book(ISBN : myISBN, name : myname, state: States.AVAILABLE)
		commentDesc = 'Buen libro'
		
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
}
