package com.library

import java.util.Date;

import com.library.exceptions.*
import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
	
	User user
	User userTwo
	User userThree
	Location location
	Reservation reservation
	Book aBook
	Library aLibrary
	
    protected void setUp() {
        super.setUp()
		
		location = new Location(country: "countryTest", street: "streetTest")
		user = new User(name: "Ariel", location: location)
		userTwo = new User(name: "Nestor", location: location)
		userTwo.addUserComment(user, "Buen usuario", 7)
		aLibrary = new Library(libraryId: "BA_Ateneo", name: "El Ateneo")
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
		assertEquals 1,userFound.commentsRcvd?.size()
		assertEquals "Buen usuario",userFound.commentsRcvd?.get(0).description
		assertEquals 7,userFound.commentsRcvd?.get(0).score
		assertEquals 1,userTwo.commentsDone?.size()
		assertEquals "Buen usuario",userTwo.commentsDone?.get(0).description
    }
	
	void testScoreOnUsers(){
		assertTrue user.validate()
		userThree = new User(name: "Gonzalo", location: location)
		userThree.addUserComment(user, "Pesimo", 1)
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals 4.0,userFound.lookScore()
		
	}
	
	void testUserMakeReservation() {
		assertTrue user.validate()
		
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		assertNotNull aBook.save()
		user.makeReservation(aBook)
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals 1,userFound.reservations?.size()
		assertEquals States.RESERVED,userFound.reservations?.get(0).book.state
	}
	
	void testReturnABook(){
		
		assertTrue user.validate()
		
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		user.returnBook(aBook)
		assertEquals 1,user.reservations?.size()
		assertEquals States.AVAILABLE,aBook.getState()
		assertEquals "M",user.reservations?.get(0).book.name
		
	}
	
	
	void testUserTryToReservateAnAlreadyReservedBook(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.RESERVED,library:aLibrary)
		shouldFail(BookAlreadyReservedException){
			user.makeReservation(aBook)
		}
	}
	
	void testUserTryToCommentItself(){
		assertTrue user.validate()
		shouldFail(UserCannotCommentItselfException){
			user.addUserComment user, "Soy el mejor", 10
		}
	}
	
	void testUserTryToReturnBookNotReservedByHim(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.RESERVED,library:aLibrary)
		shouldFail(ReservationDoesNotExistException){
			user.returnBook(aBook)
		}
	}
	
	void testUserRegisterMoreThanOneBook(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		assertEquals 2,user.getReservations().size()
	}
	
	void testUserCancelAReservation(){
		assertTrue user.validate()
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		def aBookTwo = new Book(name:"M",ISBN:"2",state:States.AVAILABLE,library:aLibrary)
		user.makeReservation(aBook)
		user.makeReservation(aBookTwo)
		user.cancelReservation(aBook)
		assertEquals 1,user.getReservations().size()
	}
	
	void testUserCommentABook() {
		assertTrue user.validate()
		assertNotNull user.save()
	
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		
		assertTrue aBook.validate()
		assertNotNull aBook.save()
		
		User userFound = User.get(user.id)
		Book bookFound = Book.get(aBook.id)
		
		userFound.addBookComment bookFound, "Pesimo Libro", 1
		assertNotNull aBook.save()
		
		Book bookFoundAgain = Book.get(bookFound.id)
		assertEquals "Pesimo Libro", bookFoundAgain.getComments().get(0).description
		assertEquals "Ariel", bookFoundAgain.comments.get(0).sourceUser.name
	}
	
	void testUserCategorizeABook(){
		
		assertTrue user.validate()

		assertNotNull aLibrary.save()
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		
		assertTrue aBook.validate()

		assertNotNull aBook.save()
		user.categorizeBook(aBook, "terror")
		user.categorizeBook(aBook, "comedia")
		assertNotNull aBook.save()
		Book bookFound = Book.get(aBook.id)
		assert ['terror','comedia'] == bookFound.tags
		
	}
	
	void testUserTryToFindSimilars(){
		assertTrue user.validate()
		assertNotNull aLibrary.save()
		
		Book a = new Book(name:"A",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		Book b = new Book(name:"B",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		Book c = new Book(name:"C",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		Book d = new Book(name:"D",ISBN:"1",state:States.AVAILABLE,library:aLibrary)
		
		assertNotNull a.save()
		assertNotNull b.save()
		assertNotNull c.save()
		assertNotNull d.save()
		
		user.categorizeBook(a, "terror")
		user.categorizeBook(a, "comedia")
		user.categorizeBook(b, "drama")
		user.categorizeBook(b, "comedia")
		user.categorizeBook(c, "thriller")
		user.categorizeBook(c, "suspenso")
		user.categorizeBook(d, "terror")
		user.categorizeBook(d, "comedia")
		
		def books = new ArrayList<Book>()
		
		books = user.lookSimilars(a)
		
		assert books.contains(d)
		
	}
	

}
