package com.library

import org.hibernate.validator.AssertTrue;

import grails.test.*

class LibraryLocationIntegrationTests extends GroovyTestCase {
	String libIdTest
	String nameTest
	String countryTest
	String streetTest 
	String urlTest
	String otherNameTest
	String otherCountryTest
	
	Library library
	Location location
	
    protected void setUp() {
        super.setUp()
		libIdTest = 'BA_Ateneo'
		nameTest = 'Ateneo'
		countryTest = 'Argentina'
		streetTest = 'Florida'
		urlTest
		otherNameTest = 'Other Ateneo'
		otherCountryTest = 'Brazil'
		
		library = new Library(libraryId: libIdTest, name: nameTest)
		location = new Location(country: countryTest, street: streetTest)
		library.setLocation location
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNewLibraryWithLocation() {
	
		assertTrue library.validate()
		assertNotNull library.save()
		Library libraryFound = Library.get(library.id)
		assertEquals nameTest, libraryFound.getName()
		assertEquals countryTest, libraryFound.getLocation().getCountry()
    }
	
	void testModifLibraryWithLocation() {
		
		assertTrue library.validate()
		assertNotNull library.save()
		
		Library libraryFound = Library.get(library.id)
		assertEquals nameTest, libraryFound.getName()
		assertEquals countryTest, libraryFound.getLocation().getCountry()
		
		libraryFound.setHomepage urlTest
		libraryFound.setName otherNameTest
		libraryFound.getLocation().setCountry otherCountryTest
		
		assertNotNull library.save()
		
		Library libraryFoundAgain = Library.get(library.id)
		assertEquals otherNameTest, libraryFoundAgain.getName()
		assertEquals otherCountryTest, libraryFoundAgain.getLocation().getCountry()
	}
	
	void testDeleteLibraryWithLocationByCascade() {
		
		assertTrue library.validate()
		assertNotNull library.save()
			
		Library libraryFound = Library.get(library.id)
		assertNotNull libraryFound
				
		libraryFound.delete()
		
		assertFalse Library.exists(libraryFound.id)
		assertFalse Location.exists(libraryFound.getLocation().id)
	}
}
