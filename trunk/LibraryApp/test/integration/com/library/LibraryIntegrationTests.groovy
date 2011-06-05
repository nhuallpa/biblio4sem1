package com.library

import grails.test.*

class LibraryIntegrationTests extends GroovyTestCase {
	
	String libId
	String nameNew 
	String nameModif 
	
    protected void setUp() {
        super.setUp()
		nameNew = 'Ateneo'
		nameModif = 'Elisseo'
		libId = 'ateneoBA'
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNewLibrary() {
		Library library = new Library()
		library.setLibraryId libId
		library.setName nameNew
		assertNotNull library.save()
		assertNotNull library.getLibraryId()
		
		Library foundLibrary = Library.get(library.id)
		assertEquals libId, foundLibrary.getLibraryId()
    }
	
	void testModifLibrary() {
		Library library = new Library()
		
		library.setLibraryId libId 
		library.setName nameNew
		assertNotNull library.save()
		
		Library foundLibrary = Library.get(library.id)
		foundLibrary.setName nameModif
		foundLibrary.save()
		
		Library libraryModif = Library.get(foundLibrary.id)
		assertEquals nameModif, libraryModif.getName()
	}
	
	void testDeleteLibrary() {
		Library library = new Library()
	
		library.setLibraryId libId
		library.setName nameNew
		assertNotNull library.save()
		
		Library foundLibrary = Library.get(library.id)
		foundLibrary.delete()
		
		assertFalse Library.exists(foundLibrary.id)
	}
}	
