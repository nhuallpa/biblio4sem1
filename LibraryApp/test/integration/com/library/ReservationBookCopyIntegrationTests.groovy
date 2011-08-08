package com.library

import grails.test.*

class ReservationBookCopyIntegrationTests extends GroovyTestCase {
	
	String nameLibrary;
	long bookISBN
	String bookName
	
	Library aLibrary
	Book aBook
	
    protected void setUp() {
        super.setUp()
		
//		nameLibrary = "Congreso SRL"
//		bookISBN = 12121212;
//		bookName = "CookBook Rails"
//		
//		
//		aLibrary = new Library(libraryId:"CONG", name:nameLibrary)
//		assertNotNull aLibrary.save()
//		aBook = new Book(name:bookName, ISBN:bookISBN, state:States.AVAILABLE)
//		
//		// TODO: A BORRAR
//		aLibrary.addToBooks(aBook)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {

    }
}
