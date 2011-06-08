package com.library

class Book {
	
	long bookId
	String title
	String subject
	long ISBN
	String state
	List<Comment> listOfComments
	List<String> tags
	Library library

	static constraints = {
		bookId(blank : false, unique: true)
		ISBN(blank : false)
	}
	
	static mapping = {
		library lazy: false
	}
	
	List<Book> similarsToMe(){
		
	}
	
		
	static hasMany = [comment : Comment]

}
