package com.library

import grails.test.*

class LocationIntegrationTests extends GroovyTestCase {
	
	String countryTest
    protected void setUp() {
        super.setUp()
		countryTest = 'USA'
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
		Location location = new Location(country: countryTest);
		assertTrue location.validate()
		assertNotNull location.save()
		
		Location locationFound = Location.get(location.id)
		assertEquals countryTest, locationFound.getCountry()
    }
}
