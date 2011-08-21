package com.library

import grails.test.*

class LibraryReservationTests extends GroovyTestCase {
	
	User user
	Book book
	Book bookTwo;
	Reservation reservation
	Library library
	
    protected void setUp() {
        super.setUp()
		library = new Library(libraryId:'BA_ATENEO', name:'Ateneo')
		user	= new User(userId:'NHUALLPA', name:'Nestor')
		book	= new Book(ISBN:'0000000', name:'Dummys', state:States.AVAILABLE)
		bookTwo = new Book(ISBN:'0000001', name:'Grails', state:States.AVAILABLE);
		
		assertNotNull book.save()
		assertNotNull bookTwo.save()
		
		assertNotNull user.save()
		assertNotNull library.save()
		library.addBookCopyOf(book)
		library.addBookCopyOf(book)
		library.addBookCopyOf(bookTwo)
		library.addBookCopyOf(bookTwo)
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddReservationSizeGood() {
		user.makeReservation(book, library)
		assertEquals 1, Library.get(library.id).reservations.size()
    }
	
	void testAddReservationMultipleSizeGood() {
		user.makeReservation(book, library)
		user.makeReservation(bookTwo, library)
		
		assertEquals 2, Library.get(library.id).reservations.size()
	}	
	
	void testAddReservationAndAccessing() {
		user.makeReservation(book, library)
		
		def bookReservationsFound = Library.get(library.id).reservations.collect {it.bookCopy}
		assertEquals 1, bookReservationsFound.size()
		assertEquals book.ISBN, bookReservationsFound.get(0).getBookMaster().ISBN
	}
	
	void testAddReservationMultipleAndAccessing() {
		user.makeReservation(book, library)
		user.makeReservation(bookTwo, library)
		
		def bookReservationsFound = Library.get(library.id).reservations.collect {it.getBookCopy()}
		
		assertEquals 2, bookReservationsFound.size()
		
		def ISBN_list = [bookReservationsFound.get(0).getBookMaster().ISBN, bookReservationsFound.get(1).getBookMaster().ISBN]
		assertTrue(ISBN_list.contains(book.ISBN))
		assertTrue(ISBN_list.contains(bookTwo.ISBN))
	}
	
	void testReservationsEmpty() {
		assertEquals null, Library.get(library.id).reservations
	}
}
