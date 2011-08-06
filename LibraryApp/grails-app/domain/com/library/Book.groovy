package com.library

import com.library.exceptions.*
import org.grails.taggable.*

class Book implements Taggable{
	
	static searchable = true
	long ISBN
	States state
	String name
	String subject
	String description
	String author
	long rating
	long totalVotes

	List<Comment> comments = new ArrayList<Comment>()
	Library library

	static constraints = {
		ISBN(blank : false)
		comments(nullable:true)
		subject(nullable:true)
		author(nullable:true)
		description(nullable:true)
		state(inList:States.list())
	}

	static hasMany = [comments : Comment]
	
	static belongsTo = [library:Library]
	
	static mapping = {
		library lazy: false
	}
	
	public String toString() {
		return name
	}
	
	List<Book> similarsToMe(){
		def books = Book.findAllByTag(this.tags.get(0))
		return books
		
	}
	void reserveMe(){
		this.state = States.RESERVED
	}

	void addComment(Comment aComment){
		this.addToComments(aComment)
		def average = (aComment.getScore() + this.rating*this.totalVotes)/ (this.totalVotes + 1)
		this.rating = average
		this.totalVotes += 1
	}
	
	void deleteComment(Comment aComment){
		def commentFound = null;
		for (comment in this.comments) {
			if (comment.equals(aComment)) {
				commentFound = comment
				break
			}
		}
		
		if (commentFound) {
			this.comments.remove commentFound
//			commentFound.delete()
		} else {
			throw new CommentDoesNotExistException()
		}
	}
	
//	void deleteComment(Comment aComment){
//		def flag = 0
//		for ( o in this.comments){
//			if ( o.equals(aComment) ){
//				this.comments.remove o
//				flag = 1
//			}
//		}
//		if (flag == 0) throw new CommentDoesNotExistException()
//		
////		aComment.sourceUser.deleteComment(aComment)
//	}
	
	void returnMe(){
		this.state = States.AVAILABLE
	}
	
	void cancelReservation(){
		this.state = States.AVAILABLE
	}
	
	void categorizeMe(String aCategory){
		this.addTag(aCategory)
	}
	
	void retireMe(){
		this.state = States.DELIVERED
	}
	
	Boolean isReserved(){
		return (state == States.RESERVED)
	}
	
	boolean equals(Object aBook) {
		return (this.id == aBook.id)
	}
		
}
