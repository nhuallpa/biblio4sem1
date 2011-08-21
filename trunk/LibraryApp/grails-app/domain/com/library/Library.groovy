package com.library

import java.util.List;



class Library {

	String libraryId
	String name
//	byte[] photo
	String homepage
	String email
	String phone	
	Location location

	static hasMany = [reservations : Reservation, bookCopys:BookCopy]

    static constraints = {
		libraryId(size: 3..20, unique: true)
		name(size: 3..60)
//		photo(nullable: true)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		phone(nullable: true)
		location(nullable: true)
		reservations(nullable:true)
		bookCopys(nullable:true)
    }
	
	static mapping = {
		location lazy: false
	}
	
	/**
	 * Take a Book and create a Copy of this Book. The state of this copy
	 * will be AVAILABLE
	 * */
	void addBookCopyOf(Book aBook) {
		BookCopy aBookCopy = new BookCopy(state:States.AVAILABLE)
		aBook.addToBookCopys(aBookCopy)
		this.addToBookCopys(aBookCopy)
	}

	Boolean isNearOf(Location location, Integer maxDistanceKm) {
		return false;
	}
	
	String toString(){
		return this.name
	}
	
	
	BookCopy getBookCopyAvailable(Book aBook){
		
//		def bookCopysFiltered = BookCopy.findAllByLibraryAndBookMaster(this, aBook)
		
		def bookCopysFiltered = this.bookCopys.findAll{it.getBookMaster() == aBook}
		
		
		BookCopy bookCopyAvailable = null;
		for (BookCopy bookCopy : bookCopysFiltered) {
			if (bookCopy.getState() == States.AVAILABLE) {
				bookCopyAvailable = bookCopy
				break;
			}
		}
		
		return bookCopyAvailable;
	}
}
