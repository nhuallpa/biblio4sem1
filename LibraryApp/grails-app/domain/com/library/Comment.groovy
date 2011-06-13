package com.library

class Comment {
	
	String description
	Date date
	def thingCommented
	User sourceUser
	Integer score
		

	static constraints = {
		description (blank : false, nullable: true)
		date(nullable:true)
		score(nullable:true)
		sourceUser(nullable:true)
		thingCommented(nullable:true)
	}
	
	static belongsTo = [sourceUser : User]
	
	void addUser(User srcUser){
		this.sourceUser = srcUser
	}


}
