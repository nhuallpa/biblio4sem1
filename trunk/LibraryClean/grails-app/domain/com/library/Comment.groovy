package com.library

class Comment {
	
	String description
	Date date
	User user
	User sourceUser
	Integer score
		

	static constraints = {
		description (blank : false)
		date(nullable:true)
		score(nullable:true)
		sourceUser(nullable:true)
		user(nullable:true)
	}
	
	static belongsTo = [User]


}
