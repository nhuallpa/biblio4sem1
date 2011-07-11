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
		
		assertNotNull user.save()
		assertNotNull library.save()
		library.addToBooks(book)		// implicitamente grails hace un save() del libro
		library.addToBooks(bookTwo)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddReservationSizeGood() {
		Reservation reservation = new Reservation(book, user)
		library.addToReservations(reservation)
		
		assertEquals 1, Library.get(library.id).reservations.size()
    }
	
	void testAddReservationMultipleSizeGood() {
		Reservation reservation = new Reservation(book, user)
		library.addToReservations(reservation)
		
		Reservation reservationTwo = new Reservation(bookTwo, user)
		library.addToReservations(reservationTwo)
		
		assertEquals 2, Library.get(library.id).reservations.size()
	}	
	
	void testAddReservationAndAccessing() {
		Reservation reservation = new Reservation(book, user)
		library.addToReservations(reservation)
		
		def bookReservationsFound = Library.get(library.id).reservations.collect {it.book}
		assertEquals 1, bookReservationsFound.size()
		assertEquals book.ISBN, bookReservationsFound.get(0).ISBN
	}
	
	void testAddReservationMultipleAndAccessing() {
		Reservation reservation = new Reservation(book, user)
		library.addToReservations(reservation)
		
		Reservation reservationTwo = new Reservation(bookTwo, user)
		library.addToReservations(reservationTwo)
		
		def bookReservationsFound = Library.get(library.id).reservations.collect {it.book}
		
		assertEquals 2, bookReservationsFound.size()
		
		def ISBN_list = [bookReservationsFound.get(0).ISBN, bookReservationsFound.get(1).ISBN]
		assertTrue(ISBN_list.contains(book.ISBN))
		assertTrue(ISBN_list.contains(bookTwo.ISBN))
	}
	
	void testReservationsEmpty() {
		assertEquals null, Library.get(library.id).reservations
	}
}
