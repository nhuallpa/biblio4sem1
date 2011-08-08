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
		aBook = new Book(name:bookName, ISBN:bookISBN, state:States.AVAILABLE)
		
		// TODO: A BORRAR
		aLibrary.addToBooks(aBook)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateBookCopyInOneLibrary() {				

		def aBookFound = Book.findByName(bookName)
		def aLibraryFound = Library.get(aLibrary.id)
		assertNotNull aBookFound
		assertNotNull aLibraryFound
		
		BookCopy aBookCopy = new BookCopy(state:States.AVAILABLE)
		aBookFound.addToBookCopys(aBookCopy)
		aLibraryFound.addToBookCopys(aBookCopy)
		
		assertNotNull aBookFound.bookCopys
		assertNotNull aLibraryFound.bookCopys
		
		
		Library aLibraryRetrive = Library.get(aLibrary.id)
		Book aBookRetrive = Book.get(aBookFound.id)
		assertNotNull aBookRetrive.bookCopys
		assertNotNull aLibraryRetrive.bookCopys
    }
	
	void testCreateBookCopyInTwoLibrary() {

		def aBookFound = Book.findByName(bookName)
				
		BookCopy aBookCopy = new BookCopy(state:States.AVAILABLE)
		aBookFound.addToBookCopys(aBookCopy)
		aLibrary.addToBookCopys(aBookCopy)
		
		BookCopy aBookCopy2 = new BookCopy(state:States.AVAILABLE)
		aBookFound.addToBookCopys(aBookCopy2)
		aLibrary.addToBookCopys(aBookCopy2)
		
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

		BookCopy aBookCopy = new BookCopy(state:States.AVAILABLE)
		aBookFound.addToBookCopys(aBookCopy)
		aLibrary.addToBookCopys(aBookCopy)
		
		Book bookRetrive = Book.findByName(bookName)
		
		def listBookCopies = bookRetrive.bookCopys.collect { it }
		def aBookCopyFound = listBookCopies[0]
		assertNotNull aBookCopyFound
		assertEquals States.AVAILABLE, aBookCopyFound.getState()
		assertEquals nameLibrary, aBookCopyFound.getLibrary().getName()
		aBookCopyFound.setState(States.DELIVERED)
		aBookCopyFound.save()
		
		def aBookCopyFoundModif = BookCopy.get(aBookCopy.id)
		assertEquals States.DELIVERED, aBookCopyFound.getState()
		
	}
}

