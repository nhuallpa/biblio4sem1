package com.library

import java.util.Date;

import com.library.exceptions.*
import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
	
	User user
	User userTwo
	User userThree
	Location location
	Location locationTwo
	Location locationThree
	Reservation reservation
	Book aBook
	
    protected void setUp() {
        super.setUp()
		
		location = new Location(country: "countryTest", street: "streetTest").save()
		locationTwo = new Location(country: "countryTest", street: "streetTest").save()
		locationThree = new Location(country: "countryTest", street: "streetTest").save()
		
		user = new User(name: "Ariel", location: location)
		assertTrue user.validate()
		assertNotNull user.save()
	
		
		userTwo = new User(name: "Nestor", location: locationTwo)
		assertTrue userTwo.validate()
		assertNotNull userTwo.save()

    }

    protected void tearDown() {
        super.tearDown()
    }

	///** Nestor: por el momento no estamos comentando a otros usuarios*//
	// Tests the retrieve of a user with comments attached.
    void testNewUserWithAll() {
		userTwo.addUserComment(user, "Buen usuario", 5)
		User userFound = User.get(user.id)
		assertEquals "Ariel", userFound.getName()
		assertEquals "countryTest", userFound.getLocation().getCountry()
		assertEquals 1,userFound.commentsRcvd?.size()
		assertEquals "Buen usuario",userFound.commentsRcvd?.get(0).description
		assertEquals 5,userFound.commentsRcvd?.get(0).score
		assertEquals 1,userTwo.commentsDone?.size()
		assertEquals "Buen usuario",userTwo.commentsDone?.get(0).description
    }
	
	void testScoreOnUsers(){
		userTwo.addUserComment(user, "Buen usuario", 5)
		
		userThree = new User(name: "Gonzalo", location: locationThree)
		userThree.addUserComment(user, "Pesimo", 1)
		assertNotNull user.save()
		User userFound = User.get(user.id)
		assertEquals 3,userFound.getRating()
		
	}
		
	void testUserTryToCommentItself(){
		shouldFail(UserCannotCommentItselfException){
			user.addUserComment user, "Soy el mejor", 10
		}
	}
	
	void testUserCommentABook() {
		
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE)
		
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
		aBook = new Book(name:"C",ISBN:"1",state:States.AVAILABLE)
		
		assertTrue aBook.validate()

		assertNotNull aBook.save()
		user.categorizeBook(aBook, "terror")
		user.categorizeBook(aBook, "comedia")
		assertNotNull aBook.save()
		Book bookFound = Book.get(aBook.id)
		assert ['terror','comedia'] == bookFound.tags
		
	}
	
	void testUserTryToFindSimilars(){
		Book a = new Book(name:"A",ISBN:"1",state:States.AVAILABLE)
		Book b = new Book(name:"B",ISBN:"1",state:States.AVAILABLE)
		Book c = new Book(name:"C",ISBN:"1",state:States.AVAILABLE)
		Book d = new Book(name:"D",ISBN:"1",state:States.AVAILABLE)
		
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
	void testOwnTags(){
		def tags = "red, sporty, expensive"
		User userFound = User.get(user.id)

		assertNotNull userFound
		userFound.parseTags(tags)

		User userRetrive = User.get(userFound.id)

		assertEquals(['red','sporty','expensive'], userRetrive.tags)


	}
	
	void testFindBookByMyOwnInterested(){
		User userFound = User.get(user.id)
		assertNotNull userFound

		userFound.addMyPreferencesTags("red, sporty, expensive");

		User userRetrieved = User.get(user.id)

		// creo los libros
		Book a = new Book(name:"A",ISBN:"1",state:States.AVAILABLE).save()
		Book b = new Book(name:"B",ISBN:"2",state:States.AVAILABLE).save()
		Book c = new Book(name:"C",ISBN:"3",state:States.AVAILABLE).save()
		Book d = new Book(name:"D",ISBN:"4",state:States.AVAILABLE).save()

		// el usuario tagea los libros
		userRetrieved.categorizeBook a, "red"
		userRetrieved.categorizeBook b, "red"
		userRetrieved.categorizeBook b, "sporty"
		userRetrieved.categorizeBook c, "sporty"
		userRetrieved.categorizeBook c, "expensive"
		userRetrieved.categorizeBook d, "microsoft"


		String findByTagHQL = """
		SELECT DISTINCT book
		FROM Book book, TagLink tagLink
		WHERE book.id = tagLink.tagRef
		AND tagLink.tag.name IN (:tags)
		"""

		List tags = userRetrieved.tags.collect { it}
		assertEquals 3, tags.size

		def booksRetrieve = Book.executeQuery(findByTagHQL, [tags:tags])

		assertNotNull booksRetrieve
		assertEquals 3, booksRetrieve.size

		assertTrue booksRetrieve.any { it == a}
		assertTrue booksRetrieve.any { it == b}
		assertTrue booksRetrieve.any { it == c}


	}

	void testFindBookByMyOwnInterestedTwo(){

		User userFound = User.get(userTwo.id)
		assertNotNull userFound
		
		userFound.addMyPreferencesTags("expensive, microsoft");

		User userRetrieved = User.get(userFound.id)

		Book a = new Book(name:"A",ISBN:"1",state:States.AVAILABLE).save()
		Book b = new Book(name:"B",ISBN:"2",state:States.AVAILABLE).save()
		Book c = new Book(name:"C",ISBN:"3",state:States.AVAILABLE).save()
		Book d = new Book(name:"D",ISBN:"4",state:States.AVAILABLE).save()

		userRetrieved.categorizeBook a, "red"
		userRetrieved.categorizeBook b, "red"
		userRetrieved.categorizeBook b, "sporty"
		userRetrieved.categorizeBook c, "sporty"
		userRetrieved.categorizeBook c, "expensive"
		userRetrieved.categorizeBook d, "microsoft"

		String findByTagHQL = """
		SELECT DISTINCT book
		FROM Book book, TagLink tagLink
		WHERE book.id = tagLink.tagRef
		AND tagLink.tag.name IN (:tags)
		"""

		List tags = userRetrieved.tags.collect { it}
		assertEquals 2, tags.size

		def booksRetrieve = Book.executeQuery(findByTagHQL, [tags:tags])

		assertNotNull booksRetrieve
		assertEquals 2, booksRetrieve.size

		assertTrue booksRetrieve.any { it == c}
		assertTrue booksRetrieve.any { it == d}

	}

}
