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
		assertTrue user.validate()
		user.save()
		location = new Location(country: "countryTest", street: "streetTest")
		aLibrary = new Library(libraryId: "BA_Ateneo", name: "El Ateneo")
		assertNotNull aLibrary.save()
    }

    protected void tearDown() {
        super.tearDown()
    }

	
	void testUserMakeReservation() {
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		assertNotNull aBook.save()
		user.makeReservation(aBook)
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals 1,userFound.reservations?.size()
		assertEquals States.RESERVED,userFound.reservations?.get(0).book.state
	}
	
	void testReturnABook(){
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
		aBook = new Book(name:"C",ISBN:"1",state:States.RESERVED,library:aLibrary)
		shouldFail(ReservationDoesNotExistException){
			user.returnBook(aBook)
		}
	}
	
	void testUserRegisterMoreThanOneBook(){
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		assertEquals 2,user.getReservations().size()
	}
	
	void testUserCancelAReservation(){
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		assertNotNull aBookTwo.save()
		assertNotNull aBook.save()
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		assertEquals 2, user.getReservations().size()
		
		User userFound = User.get(user.id)
		userFound.cancelReservation(aBook)
		assertEquals 1,userFound.getReservations().size()
		

		User userFoundAgain = User.get(user.id)
		userFoundAgain.cancelReservation(aBookTwo)
		assertEquals 0,userFoundAgain.getReservations().size()
		
		User userFoundReservationCero = User.get(user.id)
		assertEquals 0,userFoundReservationCero.getReservations().size()
	}
	
	void testUserTryToReservateAnAlreadyReservedBook(){
		aBook = new Book(name:"C",ISBN:"1",state:States.RESERVED,library:aLibrary)
		shouldFail(BookAlreadyReservedException){
			user.makeReservation(aBook)
		}
	}
	

}
