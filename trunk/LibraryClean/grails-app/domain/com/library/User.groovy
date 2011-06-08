package com.library

class User {

	String name
	byte[] photo
	String homepage
	String email
	String phone
	List<Integer> score = new ArrayList<Integer>()
	List<Reservation> reservations = new ArrayList<Reservation>()
	List<Comment> comments = new ArrayList<Comment>()
	
	
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
		this.addReservation(aReservation,aBook)
		
	}
	
	/* Alguien tiene que crearlo en la library */
	void returnBook (Book aBook){
		aBook.returnMe()
		
		this.reservations?.remove aBook
	}
	
	void addBookComment(Book aBook, String aString, Integer score ){
		aBook.comment(this, aString, score)
			
	}
	
	void addLibraryComment(Library aLibrary, String aString, Integer score ){
		aLibrary.comment(this, aString, score)
    }
	
	void comment(User sourceUser, String aString, Integer score){
		Comment aComment = new Comment(thingCommented:this, description:aString, sourceUser:sourceUser, score: score)
		this.comments?.add(aComment)
		this.score?.add score
	}
	
	void addUserComment(User aUser, String aString, Integer score ){
		aUser.comment(this, aString, score)		
    }
	
	List<Book> lookSimilars(Book aBook){
		return aBook.similarsToMe()
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
			if ( o?.getBook() == aBook )
				reservations.remove o	
		}
	}
	
	void pullOutBook(aBook){
		//Check if is reserved to this user
		aBook.retireMe()
		
	}
	
	Float lookScore(){
		Integer i = score?.sum()	
		Integer d = score?.size()
        if (score?.size() != null)
			return i.div(d)
	}
	
	void returnBook(aBook){
		aBook.returnMe()
		
	}
	
	void addReservation(Reservation aReservation, Book aBook){
		aBook.reserveMe()
		this.reservations?.add aReservation

	}
	
	List<Book> lookBooksOnCategory(String tag){
		
	}
	
	
	

	
}
