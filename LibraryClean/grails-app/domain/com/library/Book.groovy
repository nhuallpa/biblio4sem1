package com.library

class Book {
	
//	long bookId //que lo tenga la BD nada mas
	String title
	String subject
	long ISBN
	String state
	List<Comment> listOfComments
	List<String> tags
	Library library

	static constraints = {
		//bookId(blank : false, unique: true)
		ISBN(blank : false)
		tags(nullable: true)
		listOfComments(nullable:true)
		subject(nullable:true)
	}
	
	static mapping = {
		library lazy: false
	}
	
	List<Book> similarsToMe(){
		
	}
	void reserveMe(){
		
	}
		
	static hasMany = [comment : Comment]

}