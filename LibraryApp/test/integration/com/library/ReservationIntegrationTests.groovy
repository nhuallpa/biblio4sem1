/**
 * Ariel Liguori, V1.0
 * Nestor Huallpa, V1.1: we should reserve a copy of that book
 * */

package com.library

import com.library.exceptions.BookAlreadyReservedException;
import com.library.exceptions.ReservationDoesNotExistException;

import grails.test.*

class ReservationIntegrationTests extends GroovyTestCase {
	
	User user
	Library libraryAteno
	Library libraryColon
	
	Book aBook1
	Book aBook2
	
	Location location
	
    protected void setUp() {
        super.setUp()
		user = new User(name: "Ariel", location: location)
		assertTrue user.validate()
		user.save()
		location = new Location(country: "countryTest", street: "streetTest")
		
		libraryAteno = new Library(libraryId: "BA_Ateneo", name: "El Ateneo")
		assertNotNull libraryAteno.save()
		libraryColon = new Library(libraryId: "BA_Colon", name: "El Colon")
		assertNotNull libraryColon.save()
		
		
		aBook1 = new Book(name:"F",ISBN:"1111",state:States.AVAILABLE)
		aBook2 = new Book(name:"C",ISBN:"1222",state:States.AVAILABLE)
		assertNotNull aBook1.save()
		assertNotNull aBook2.save()
		
		
		libraryAteno.addBookCopyOf(aBook1)
		libraryAteno.addBookCopyOf(aBook1)
		libraryAteno.addBookCopyOf(aBook2)
		
		libraryColon.addBookCopyOf(aBook1)
		libraryColon.addBookCopyOf(aBook2)
		libraryColon.addBookCopyOf(aBook2)
		libraryColon.addBookCopyOf(aBook2)
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testUserMakeReservationABookCopy() {
		
		user.makeReservation(aBook1, libraryAteno)
		
		assertEquals 1, user.reservations.size()
		
		assertEquals "F", user.reservations.get(0).nameOfBook()
		assertEquals States.RESERVED, user.reservations.get(0).stateOfBook()
	}
	

	void testUserRegisterMoreThanOneBook(){
		
		user.makeReservation(aBook1, libraryAteno)
		user.makeReservation(aBook2, libraryColon)
		assertEquals 2,user.getReservations().size()
		assertTrue user.getReservations().any{it.nameOfBook() == aBook1.name}
		assertTrue user.getReservations().any{it.nameOfBook() == aBook2.name}
	}
	
	void testUserCancelAReservation(){
		
		user.makeReservation(aBook1, libraryAteno)
		user.makeReservation(aBook2, libraryColon)
		
		User userFound = User.get(user.id)
		userFound.cancelReservation(aBook1)
		assertEquals 1,userFound.getReservations().size()
		

		User userFoundAgain = User.get(user.id)
		userFoundAgain.cancelReservation(aBook2)
		assertEquals 0,userFoundAgain.getReservations().size()
		
		User userFoundReservationCero = User.get(user.id)
		assertEquals 0,userFoundReservationCero.getReservations().size()
	}
	
	
	void testDeliverBookToUser(){
		
		user.makeReservation(aBook1, libraryAteno)
		User userFound = User.get(user.id)

		assertEquals 1, userFound.getReservations().size()
		
		Reservation reservation = userFound.reservations.get(0)
		
		reservation.deliverBook()
		
		User userFoundAgain = User.get(user.id)
		Reservation reservationCanged = user.reservations.get(0)
		
		assertEquals aBook1.name, reservationCanged.nameOfBook()
		assertEquals States.DELIVERED, reservationCanged.stateOfBook()
		
	}
	
	
	void testReturnABook(){
		user.makeReservation(aBook1, libraryAteno)
		User userFound = User.get(user.id)
		Reservation reservation = userFound.reservations.get(0)
		reservation.deliverBook()
		assertEquals States.DELIVERED, reservation.stateOfBook()
		
		def bookCopy = reservation.getBookCopy()
		int bookCopyId = bookCopy.id	
		
		assertTrue userFound.existReservation(reservation)
		
		// El usuario regresa el libro
		User userRetrived = User.get(user.id)
		assertEquals States.DELIVERED, BookCopy.get(bookCopyId).getState()
		
		userRetrived.returnBook(bookCopy)
		
		// Chequeo la consistencia
		assertFalse userRetrived.existReservation(reservation)
		assertEquals States.AVAILABLE, BookCopy.get(bookCopyId).getState()
	}
	
	void testUserTryToReservateAnAlreadyReservedBook(){
		user.makeReservation(aBook1, libraryAteno)
		shouldFail(BookAlreadyReservedException){
			user.makeReservation(aBook1, libraryAteno)
		}
	}

}

