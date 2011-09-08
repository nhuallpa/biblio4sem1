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
	List<BookCopy> bookCopys = new ArrayList<BookCopy>()

	static constraints = {
		ISBN(blank : false)
		comments(nullable:true)
		bookCopys(nullable:true)
		subject(nullable:true)
		author(nullable:true)
		description(nullable:true)
		state(inList:States.list())
	}

	static hasMany = [comments : Comment, bookCopys:BookCopy]

	
	static mapping = {

	}
	
	public String toString() {
		return name
	}
	
	@Deprecated
	List<Book> similarsToMe(){
		def books = Book.findAllByTag(this.tags.get(0))
		return books
	}

	void addComment(Comment aComment){
		this.addToComments(aComment)
		def average = (aComment.getScore() + this.rating*this.totalVotes)/ (this.totalVotes + 1)
		this.rating = average
		this.totalVotes += 1
	}
	
	void deleteComment(Comment aComment){
		if (!(this.comments as ArrayList<Comment>).contains(aComment)) {
			throw new CommentDoesNotExistException()
		} else {
			this.comments.remove aComment
		}
	}
	
	void categorizeMe(String aCategory){
		this.addTag(aCategory)
	}
	
	boolean equals(Object aBook) {
		return (this.ISBN == aBook.ISBN)
	}
		
}
