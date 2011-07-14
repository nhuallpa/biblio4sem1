package com.library

import com.library.exceptions.*
import org.grails.taggable.*

class Book implements Taggable{
	
	static searchable = true	
	static hasMany = [comments : Comment]

	String name
	String subject
	long ISBN
	States state
	List<Comment> comments = new ArrayList<Comment>()
	Library library
	long rating
	long totalVotes

	static constraints = {
		ISBN(blank : false)
		comments(nullable:true)
		subject(nullable:true)
		state(inList:States.list())
	}

	static belongsTo = [library:Library]
	
	static mapping = {
		library lazy: false
	}
	
	List<Book> similarsToMe(){
		def books = Book.findAllByTag(this.tags.get(0))
		return books
		
	}
	void reserveMe(){
		this.state = States.RESERVED
	}

	
// @Nestor comment this because it is not using
//	void addComment(User srcUser, String aString, Integer score){
//		def comment = new Comment(description : aString)
//		comment.addUser srcUser
//		comments.add comment
//	}
	
	void addComment(Comment aComment){
		
//		def aComment = new Comment(description: aString, 
//								   sourceUser: sourceUser, 
//								   score: score,
//								   date:new Date())
		this.comments?.add(aComment)
		def average = (aComment.getScore() + this.rating*this.totalVotes)/ (this.totalVotes + 1)
		this.rating = average
		this.totalVotes += 1
	}
	
	void deleteComment(Comment aComment){
		def flag = 0
		for ( o in this.comments){
			if ( o.equals(aComment) ){
				this.comments.remove o
				flag = 1
			}
		}
		if (flag == 0) throw new CommentDoesNotExistException()
		
		aComment.sourceUser.deleteComment(aComment)
	}
	
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
		
}
