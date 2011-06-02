package com.library

import grails.test.*

class LibraryIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFirstSaveEver() {
		def library = new Library()
		library.setLibraryId '2'
		library.setName 'Ateneo'
		library.setFounder 'Belgrano'
		assertNotNull library.save()
		assertNotNull library.getLibraryId()
		
		def foundLibrary = Library.get(library.getLibraryId())
		assertEquals '2', foundLibrary.getLibraryId()
    }
}
