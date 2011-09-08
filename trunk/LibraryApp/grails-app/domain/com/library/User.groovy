package com.library

import org.grails.taggable.*
import com.library.exceptions.*

class User implements Taggable{
	
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
	
	public String toString(){
		return name
	}
	
	void makeReservation(Book aBook, Library aLibrary) {

		if (this.isReserved(aBook)) throw new BookAlreadyReservedException()
		
		BookCopy aBookCopyAvailable = aLibrary.getBookCopyAvailable(aBook)
		if (!aBookCopyAvailable) throw new NotExistBookCopyAvailable() 
		
		Reservation aReservation = new Reservation(aBookCopyAvailable, this)
		this.addReservation(aReservation)
		aLibrary.addToReservations(aReservation)
		this.save()
	}

	private Boolean isReserved(Book aBook) {
		return reservations.any { it.getBookCopy().getBookMaster() == aBook};
	}
	
	/* Alguien tiene que crearlo en la library */
	@Deprecated
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
		this.commentsDone.add aComment
		
	}
	@Deprecated
	void comment(User sourceUser, String aString, Integer score){
		Comment aComment = new Comment(description:aString, sourceUser:sourceUser, score: score)
		this.commentsRcvd?.add(aComment)
		def average = (score + this.rating*this.totalVotes)/ (this.totalVotes + 1)
		this.rating = average
		this.totalVotes += 1
	}
	@Deprecated
	void addUserComment(User aUser, String aString, Integer score ){
		if ( this.equals(aUser)) throw new UserCannotCommentItselfException()
		def comment = new Comment(sourceUser: this, description:aString, score: score)
		this.commentsDone.add(comment)
		aUser.comment(this, aString, score)		
    }
	@Deprecated
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
		def reservationFound = this.reservations.find{it.getBookCopy().bookMaster == aBook}

		if (reservationFound) {
			this.reservations.remove reservationFound
			reservationFound.getBookCopy().cancelReservation()
			reservationFound.delete()
		} else {
			throw new ReservationDoesNotExistException()
		}
	}
	
	void deleteMyComment(Comment aComment){
		if (!(this.commentsDone as ArrayList<Comment>).contains(aComment)) {
			throw new CommentDoesNotExistException()
		} else {
			this.commentsDone.remove aComment
		}
	}
	@Deprecated
	void deleteComment(Comment aComment){
		
		def flag = 0
		for ( o in this.commentsRcvd){
			if ( o == aComment ){
				this.commentsRcvd.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new CommentDoesNotExistException()		
	}
	@Deprecated
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
	
	private void addReservation(Reservation aReservation){
		Date date = new Date()
		aReservation.reservationDate = date
		this.reservations?.add aReservation
		aReservation.save()
	}
	
	@Deprecated
	void addUserLocation(String country, String city, String address){
		Location aLocation = new Location(country: country, city: city, street: address)
		this.location = aLocation
	}
	
	@Deprecated
	String seeAddress(){
		if (this.location == null) return "Buenos Aires, Paseo Colon 850"
		return this.location.address()
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		if(user.getName().equals(this.name) && user.getPassword().equals(this.password)){
			return true;
		} 
		return false;
	}

	/**
	 * Apply save before use it method
	 * */
	void addMyPreferencesTags(String tags) {
		this.parseTags(tags)
	}
	
}
