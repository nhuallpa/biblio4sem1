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
		score lazy: false
	}
	
	static hasMany = [comments : Comment, reservations : Reservation]
	
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
		aBook.comment(this, aString, score)
		def comment = new Comment(sourceUser: this, description:aString, score: score)
		this.commentsDone.add(comment)
		
	}
	
	//agregado por Gonzalo.(para probar)
	void addToBookComment(Book aBook, Comment aComment){
		aBook.comment(this, aComment.getDescription(), aComment.getScore())
		this.commentsDone.add(aComment)
		
	}

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
		for ( o in this.commentsDone){
			if ( o.equals (aComment) ){
				this.commentsDone.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new CommentDoesNotExistException()
		else {
			aComment.sourceUser.deleteComment(aComment)
		}
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
		else {
				aComment.sourceUser.deleteComment(aComment)
			}
		
		
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
	
	
	//  Leaved for further updates, first the basics.
	//	void addLibraryComment(Library aLibrary, String aString, Integer score ){
	//		aLibrary.comment(this, aString, score)
	//   }

	
}
