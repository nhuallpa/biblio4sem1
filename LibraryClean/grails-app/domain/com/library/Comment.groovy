package com.library

class Comment {
	
	static belongsTo = Book
	
	String description
	Date date
	User user
	float score
		

	static constraints = {
		description (blank : false)
		date(nullable:true)
		score(nullable:true)
		
	}

}
