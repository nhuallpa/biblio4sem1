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
		
	}
	
	void addBookComment(Book aBook, String aString ){
			
	}
	
	void addLibraryComment(Library aLibrary, String aString ){
	
    }
	
	void addUserComment(User aUser, String aString ){
	
    }
	
	List<Book> lookSimilars(Book aBook){
		
	}
	
	long getScore(){
		return this.score
	}
	
	void categorizeBook(Book aBook, String tag){
		
	}
	
	List<Reservation> lookMyReservations(){
		
	}
	
	void cancelReservation(Reservation aReservation){
		
	}
	
	void addReservation(Reservation aReservation, Book aBook){
		
	}
	
	List<Book> lookBooksOnCategory(String tag){
		
	}
	
	
	

	
}
