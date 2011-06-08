package com.library

class User {

	String name
	byte[] photo
	String homepage
	String email
	String phone
	long   score
	List<Reservation> reservations
	List<Comment> comments
	
	Location location

    static constraints = {
		name(size: 3..60)
		photo(nullable: true)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true, unique: true)
		phone(nullable: true)
		location(nullable: true)
		reservations(nullable: true)
		comments(nullable: true)
		score(nullable: true)
    }
	
	static mapping = {
		location lazy: false
		reservations lazy: false
		comments lazy: false
	}
	
	static hasMany = [comments : Comment, reservations : Reservation]
	
	void makeReservation(Book aBook) {
		
		Reservation aReservation = new Reservation(aBook,this)
		addReservation(aReservation,aBook)
		
	}
	
	/* Alguien tiene que crearlo en la library */
	void returnBook (Book aBook){
		aBook.returnMe()
		
		reservations.remove aBook
	}
	
	void addBookComment(Book aBook, String aString ){
		aBook.comment(aString)
			
	}
	
	void addLibraryComment(Library aLibrary, String aString ){
		aLibrary.comment(aString)
    }
	
	void comment(String aString){
		Comment aComment = new Comment(this, aString)
		comments.add aComment
	}
	
	void addUserComment(User aUser, String aString ){
		aUser.comment(aString)		
    }
	
	List<Book> lookSimilars(Book aBook){
		return aBook.similarsToMe()
	}
	
	long getScore(){
		return this.score
	}
	
	void categorizeBook(Book aBook, String tag){
		aBook.categorizeMe()
	}
	
	List<Reservation> lookMyReservations(){
		return this.reservations
	}
	
	void cancelReservation(Book aBook){
		aBook.cancelReservation()
		
		for ( o in reservations){
			if ( o.getBook() == aBook )
				reservations.remove o	
		}
	}
	
	void pullOutBook(aBook){
		//Check if is reserved to this user
		aBook.retireMe()
		
	}
	
	void returnBook(aBook){
		
		
	}
	
	void addReservation(Reservation aReservation, Book aBook){
		aBook.reserveMe()
		reservations.add aReservation

	}
	
	List<Book> lookBooksOnCategory(String tag){
		
	}
	
	
	

	
}
