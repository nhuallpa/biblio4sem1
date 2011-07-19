package com.library

import com.library.exceptions.*

class User {
	
	String name
	String password
	byte[] photo
	String homepage
	String email
	String phone
	long rating
	long totalVotes
	List<Reservation> reservations = new ArrayList<Reservation>()
	List<Comment> commentsRcvd = new ArrayList<Comment>()
	List<Comment> commentsDone = new ArrayList<Comment>()
	
	Location location

    static constraints = {
		name(size: 3..60, nullable:true)
		password(size: 3..15, nullable:true)
		photo(nullable: true)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		phone(nullable: true)
		location(nullable: true)
		reservations(nullable: true)
		commentsRcvd(nullable: true)
		commentsDone(nullable: true)
    }
	
	static mapping = {
		location lazy: false
		reservations lazy: false
		commentsRcvd lazy: false
		commentsDone lazy: false
	}
	
	static hasMany = [commentsDone : Comment, reservations : Reservation]
	
	void makeReservation(Book aBook) {
		if (aBook.isReserved()) throw new BookAlreadyReservedException()
		Reservation aReservation = new Reservation(aBook,this)
		this.addReservation(aReservation,aBook)	
	}
	
	/* Alguien tiene que crearlo en la library */
	void returnBook (Book aBook){
		
		def flag = 0
		for ( o in this.reservations){
			if ( o?.getBook() == aBook ){
				this.reservations.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new ReservationDoesNotExistException()

		aBook.returnMe()
	}
		
	
	
	
	void addBookComment(Book aBook, String aString, Integer score ){
		
		def aComment = new Comment(description: aString,
									sourceUser: this,
									score: score,
									date:new Date())
		
		aBook.addComment(aComment)
//		def comment = new Comment(sourceUser: this, description:aString, score: score)
		this.addToCommentsDone(aComment)
	}
	
	//@gonzalo: lo agrege para probar
//	void addToBookComment(Book aBook, Comment aComment){
//		aBook.comment(this, aComment.getDescription(), aComment.getScore())
//		this.commentsDone.add(aComment)
//		this.comments?.add aComment
//	}

	void comment(User sourceUser, String aString, Integer score){
		Comment aComment = new Comment(description:aString, sourceUser:sourceUser, score: score)
		this.commentsRcvd?.add(aComment)
		def average = (score + this.rating*this.totalVotes)/ (this.totalVotes + 1)
		this.rating = average
		this.totalVotes += 1
	}
	
	void addUserComment(User aUser, String aString, Integer score ){
		if ( this.equals(aUser)) throw new UserCannotCommentItselfException()
		def comment = new Comment(sourceUser: this, description:aString, score: score)
		this.commentsDone.add(comment)
		aUser.comment(this, aString, score)		
    }
	
	List<Book> lookSimilars(Book aBook){
		return aBook.similarsToMe()
	}
	
	void categorizeBook(Book aBook, String tag){
		aBook.categorizeMe(tag)
	}
	
	List<Reservation> lookMyReservations(){
		return this.reservations
	}
	
	void cancelReservation(Book aBook){
		
		def flag = 0
		for ( o in this.reservations){
			if ( o?.getBook() == aBook ){
				this.reservations.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new ReservationDoesNotExistException()
		
		aBook.cancelReservation()	
	}
	
	void deleteMyComment(Comment aComment){
		def flag = 0
//		for ( o in comments){
		for(int i = 0; i < commentsDone.size(); i++){
			Comment obj = commentsDone.get(i)
//			if ( obj.equals(aComment) ){
			if(obj.id == aComment.id){
//				comments?.remove obj
				this.commentsDone.remove obj
				flag = 1
			}
		}
		if (flag == 0) throw new CommentDoesNotExistException()
//		else {
//			aComment.sourceUser.deleteComment(aComment)
//		}
	}
	
	void deleteComment(Comment aComment){
		
		def flag = 0
		for ( o in this.commentsRcvd){
			if ( o == aComment ){
				this.commentsRcvd.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new CommentDoesNotExistException()
//		else {
//				aComment.sourceUser.deleteComment(aComment)
//			}
		
		
	}
	
	void pullOutBook(Book aBook){
		
		def flag = 0
		for ( o in this.reservations){
			if ( o?.getBook() == aBook ){
				this.reservations.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new ReservationDoesNotExistException()
		
		aBook.retireMe()
	}
	
	void addReservation(Reservation aReservation, Book aBook){
		aBook.reserveMe()
		this.reservations?.add aReservation

	}
	
	List<Book> lookBooksOnCategory(String tag){
		def books = new ArrayList<Book>()
		books = Book.findAllByTag(tag)
		return books
	}
	
	void addUserLocation(String country, String city, String address){
		Location aLocation = new Location(country: country, city: city, street: address)
		this.location = aLocation
	}
	
	//  Leaved for further updates, first the basics.
	//	void addLibraryComment(Library aLibrary, String aString, Integer score ){
	//		aLibrary.comment(this, aString, score)
	//   }
	
	String seeAddress(){
		if (this.location == null) return "Buenos Aires, Paseo Colon 850"
		return this.location.address()
	}

	
}
