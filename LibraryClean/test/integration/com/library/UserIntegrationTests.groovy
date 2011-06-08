package com.library

import java.util.Date;
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
		aBook = new Book(title:"C",ISBN:"1",state:"Reserved",library:aLibrary)
		
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
    }
	
	void testAUserCommentsAnother() {
		assertTrue user.validate()
		user.makeReservation aBook
		assertEquals 1,user.reservations?.size()
	}
	
	
}
