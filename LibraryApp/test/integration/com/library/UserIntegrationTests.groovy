package com.library

import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
	
	User user
	User userTwo
	Location location
	Reservation reservation
	
    protected void setUp() {
        super.setUp()
		
		location = new Location(country: "countryTest", street: "streetTest")
		user = new User(name: "Ariel", location: location, score: [5])
		userTwo = new User(name: "Nestor", location: location, score: [5])
		//userTwo.addUserComment user, "Buen usuario", 2
		//reservation = new Reservation()
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNewUserWithAll() {
		assertTrue user.validate()
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals "Ariel", userFound.getName()
		assertEquals "countryTest", userFound.getLocation().getCountry()
    }
	
/*	void testAUserCommentsAnother() {
		assertTrue user.validate()
		user.addUserComment userTwo, "Bien", 4
		assertNotNull userTwo.save()
		User userFound = User.get(userTwo.id)
	}*/
	
	
}