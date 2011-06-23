package com.library

import grails.test.*

class LibraryBookTests extends GroovyTestCase {
	String libId
	String nameNew
	Library library
	
    protected void setUp() {
        super.setUp()
		nameNew = 'Ateneo'
		libId = 'ateneoBA'
		library = new Library()
		library.setLibraryId libId
		library.setName nameNew
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddReservation() {
//		Book book = new Book( name:'Grails', subject:'dev', state:'A' )
//		User user = new User( name:'nestor')
//		Reservation reservation = new Reservation(book, user)
//		library.addToReservations(reservation)
//				
//		assertNotNull library.save()
    }
	void testDeleteReservation() {
		
	}
	
}
