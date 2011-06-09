package com.library

class Comment {
	
	String description
	Date date
	def thingCommented
	User sourceUser
	Integer score
		

	static constraints = {
		description (blank : false)
		date(nullable:true)
		score(nullable:true)
//		user(blank : false)
		sourceUser(nullable:true)
		thingCommented(nullable:true)
	}
	
	static belongsTo = [User, Book]


}
