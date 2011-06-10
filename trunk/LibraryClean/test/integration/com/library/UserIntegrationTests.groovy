package com.library

import java.util.Date;

import com.library.exceptions.BookAlreadyReservedException;

import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
	
	User user
	User userTwo
	Location location
	Reservation reservation
	Book aBook
	Library aLibrary
	
    protected void setUp() {
        super.setUp()
		location = new Location(country: "countryTest", street: "streetTest")
		user = new User(name: "Ariel", location: location)
		userTwo = new User(name: "Nestor", location: location)
		userTwo.addUserComment(user, "Buen usuario", 2)
		
		aLibrary = new Library(libraryId: "BA_Ateneo", name: "El Ateneo")
		
		
    }

    protected void tearDown() {
        super.tearDown()
    }

	// Tests the retrieve of a user with comments attached.
    void testNewUserWithAll() {
		assertTrue user.validate()
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals "Ariel", userFound.getName()
		assertEquals "countryTest", userFound.getLocation().getCountry()
		assertEquals 1,userFound.comments?.size()
		assertEquals "Buen usuario",userFound.comments?.get(0).description
    }
	
	void testUserMakeReservation() {
		assertTrue user.validate()
		aBook = new Book(title:"C",ISBN:"1",state:"Available",library:aLibrary)
		user.makeReservation(aBook)
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals 1,userFound.reservations?.size()
		assertEquals "Reserved - Waiting",userFound.reservations?.get(0).state
	}
	
	
	void testUserTryToReservateAnAlreadyReservedBook(){
		assertTrue user.validate()
		aBook = new Book(title:"C",ISBN:"1",state:"Reserved - XXXX",library:aLibrary)
		shouldFail(BookAlreadyReservedException){
			user.makeReservation(aBook)
		}
	}
	
	
	
}
