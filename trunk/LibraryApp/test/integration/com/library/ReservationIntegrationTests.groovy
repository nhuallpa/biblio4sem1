package com.library

import com.library.exceptions.BookAlreadyReservedException;
import com.library.exceptions.ReservationDoesNotExistException;

import grails.test.*

class ReservationIntegrationTests extends GroovyTestCase {
	
	User user
	Book aBook
	Library aLibrary
	Location location
	
    protected void setUp() {
        super.setUp()
		
		user = new User(name: "Ariel", location: location)
		location = new Location(country: "countryTest", street: "streetTest")
		aLibrary = new Library(libraryId: "BA_Ateneo", name: "El Ateneo")
		assertNotNull aLibrary.save()
    }

    protected void tearDown() {
        super.tearDown()
    }

	
	void testUserMakeReservation() {
		assertTrue user.validate()
		
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		assertNotNull aBook.save()
		user.makeReservation(aBook)
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals 1,userFound.reservations?.size()
		assertEquals States.RESERVED,userFound.reservations?.get(0).book.state
	}
	
	void testReturnABook(){
		
		assertTrue user.validate()
		
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		user.returnBook(aBook)
		assertEquals 1,user.reservations?.size()
		assertEquals States.AVAILABLE,aBook.getState()
		assertEquals "M",user.reservations?.get(0).book.name
		
	}
    void testUserTryToReturnBookNotReservedByHim(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.RESERVED,library:aLibrary)
		shouldFail(ReservationDoesNotExistException){
			user.returnBook(aBook)
		}
	}
	
	void testUserRegisterMoreThanOneBook(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		assertEquals 2,user.getReservations().size()
	}
	
	void testUserCancelAReservation(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		user.cancelReservation(aBook)
		assertEquals 1,user.getReservations().size()
	}
	
	void testUserTryToReservateAnAlreadyReservedBook(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.RESERVED,library:aLibrary)
		shouldFail(BookAlreadyReservedException){
			user.makeReservation(aBook)
		}
	}
	

}
