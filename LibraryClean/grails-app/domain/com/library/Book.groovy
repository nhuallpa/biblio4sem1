package com.library

class Book {
	
	static searchable = true
	
	static hasMany = [tags : String, comments : Comment]
	
	
//	long bookId //que lo tenga la BD nada mas
	String title
	String subject
	long ISBN
	String state
	List<Comment> comments = new ArrayList<Comment>()
	List<String> tags = new ArrayList<String>()
	Library library

	static constraints = {
		//bookId(blank : false, unique: true)
		ISBN(blank : false)
		tags(nullable: true)
		comments(nullable:true)
		subject(nullable:true)
		library(nullable: true)
		state(nullable: true)
		
	}

	static mapping = {
		library lazy: false
	}
	
	List<Book> similarsToMe(){
		
	}
	void reserveMe(){
		
	}
	
	void addComment(User srcUser, String aString, Integer score){
		def comment = new Comment(description : aString)
		comment.addUser srcUser
		comments.add comment
	}
	
	void returnMe(){
		
	}
	
	void cancelReservation(){
		
	}
	
	void categorizeMe(){
		
	}
	
	void retireMe(){
		
	}
	
	Boolean isReserved(){
		return state.contains("Reserved")
	}
		
	

}
