package com.library

import grails.test.*

class BookCopyIntegrationTests extends GroovyTestCase {
	
	String nameLibrary;
	long bookISBN
	String bookName
	
	Library aLibrary
	Book aBook
	
    protected void setUp() {
        super.setUp()
		
		nameLibrary = "Congreso SRL"
		bookISBN = 12121212;
		bookName = "CookBook Rails"
		
		
		aLibrary = new Library(libraryId:"CONG", name:nameLibrary)
		assertNotNull aLibrary.save()
		assertNotNull aBook = new Book(name:bookName, ISBN:bookISBN, state:States.AVAILABLE).save()
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateBookCopyInOneLibrary() {				

		def aBookFound = Book.findByName(bookName)
		Library aLibraryFound = Library.get(aLibrary.id)
		assertNotNull aBookFound
		assertNotNull aLibraryFound
		
		aLibraryFound.addBookCopyOf(aBookFound)
		
		assertNotNull aBookFound.bookCopys
		assertNotNull aLibraryFound.bookCopys
		
		
		Library aLibraryRetrive = Library.get(aLibrary.id)
		Book aBookRetrive = Book.get(aBookFound.id)
		assertNotNull aBookRetrive.bookCopys
		assertNotNull aLibraryRetrive.bookCopys
    }
	
	void testCreateBookCopyInTwoLibrary() {

		def aBookFound = Book.findByName(bookName)
		
		aLibrary.addBookCopyOf(aBook)
		aLibrary.addBookCopyOf(aBook)
		
		def aBookRetrive = Book.get(aBookFound.id)
		def aLibraryRetrive = Library.get(aLibrary.id)
		
		def cantBookCopiesInBooks = 0
		aBook.getBookCopys().each { cantBookCopiesInBooks++}
		
		def cantBookCopiesInLibrary = 0
		aLibraryRetrive.getBookCopys().each { cantBookCopiesInLibrary++ }
		
		assertEquals 2, cantBookCopiesInBooks
		assertEquals 2, cantBookCopiesInLibrary
	}
	
	void testUpdateStateBookCopy() {	
		def aBookFound = Book.findByName(bookName)
		
		aLibrary.addBookCopyOf(aBookFound)
		
		Book bookRetrive = Book.findByName(bookName)
		
		def listBookCopies = bookRetrive.bookCopys.collect { it }
		def aBookCopyFound = listBookCopies[0]
		assertNotNull aBookCopyFound
		assertEquals States.AVAILABLE, aBookCopyFound.getState()
		assertEquals nameLibrary, aBookCopyFound.getLibrary().getName()
		aBookCopyFound.setState(States.DELIVERED)
		aBookCopyFound.save()
		
		assertEquals States.DELIVERED, aBookCopyFound.getState()
		
	}
}

