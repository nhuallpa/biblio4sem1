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
	List<Integer> score = new ArrayList<Integer>()
	Library library

	static constraints = {
		ISBN(blank : false)
		comments(nullable:true)
		subject(nullable:true)
		library(nullable: true)
		state(inList:States.list())
		score(nullable: true)
	}

	static belongsTo = [Library]
	
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
	
	void addComment(User srcUser, String aString, Integer score){
		def comment = new Comment(description : aString)
		comment.addUser srcUser
		comments.add comment
	}
	
	void comment(User sourceUser, String aString, Integer score){
		def aComment = new Comment(description: aString, sourceUser: sourceUser, score: score)
		this.comments?.add(aComment)
		this.score?.add(score)
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
	
	Float lookScore(){
		Integer i = score?.sum()
		Integer d = score?.size()
		if (score?.size() != null)
			return i.div(d)
	}
	
		
}
