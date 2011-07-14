package com.library

import grails.test.*

class LibraryBookIntegrationTests extends GroovyTestCase {
	String libId
	String nameNew
	Library library
	Book book;
	Book bookTwo;
	
	String nameBook = 'PLSQL'
	String nameBookTwo = 'Grails'
	
    protected void setUp() {
        super.setUp()
		nameNew = 'Ateneo'
		libId = 'ateneoBA'
		library = new Library()
		library.setLibraryId libId
		library.setName nameNew
		library.save()
		
		book = new Book(name:nameBook, state:States.AVAILABLE);
		bookTwo = new Book(name:nameBookTwo, state:States.AVAILABLE);

    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testAddBookSizeGood() {
		Library libraryFound = Library.get(library.id)
		libraryFound.addToBooks book
		
		assertEquals 1, Library.get(libraryFound.id).books.size()
	}
	void testAddBookAndAccessingBook() {
		Library libraryFound = Library.get(library.id)
		libraryFound.addToBooks book
		
		def bookName = Library.get(libraryFound.id).books.collect { it.name}
		assertEquals([nameBook], bookName)
	}
	
	void testAddMultipleBookSizeGood() {
		Library libraryFound = Library.get(library.id)
		libraryFound.addToBooks book
		libraryFound.addToBooks bookTwo
		
		assertEquals 2, Library.get(libraryFound.id).books.size()
	}
	
	void testAddMultipleAccessingBooks() {
		Library libraryFound = Library.get(library.id)
		libraryFound.addToBooks(book)
		libraryFound.addToBooks(bookTwo)
		
		def bookNames = Library.get(libraryFound.id).books.collect { it.name}
		assertTrue bookNames.contains(nameBook)
		assertTrue bookNames.contains(nameBookTwo)
	}
}
