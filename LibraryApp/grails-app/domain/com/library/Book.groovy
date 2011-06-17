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
	List<Integer> score = new ArrayList<Integer>()
	Library library

	static constraints = {
		//bookId(blank : false, unique: true)
		ISBN(blank : false)
		tags(nullable: true)
		comments(nullable:true)
		subject(nullable:true)
		library(nullable: true)
		state(nullable: true)
		score(nullable: true)
	}

	static belongsTo = [Library]
	
	static mapping = {
		library lazy: false
	}
	
	List<Book> similarsToMe(){
		
	}
	void reserveMe(){
		this.state = "Reserved"
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
	
	void returnMe(){
		this.state = "Available"
	}
	
	void cancelReservation(){
		this.state = "Available"
	}
	
	void categorizeMe(){
		
	}
	
	void retireMe(){
		this.state = "Deivered"
	}
	
	Boolean isReserved(){
		return state.contains("Reserved")
	}
	
	Float lookScore(){
		Integer i = score?.sum()
		Integer d = score?.size()
		if (score?.size() != null)
			return i.div(d)
	}
	
		
}
