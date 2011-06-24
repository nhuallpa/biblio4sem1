package com.library

class Comment {
	
	String description
	Date date
	User sourceUser
	Integer score
		

	static constraints = {
		description (blank : false, nullable: true)
		date(nullable:true)
		score(nullable:true)
		sourceUser(nullable:true)
	}
	
	static belongsTo = [User]
	
	void addUser(User srcUser){
		this.sourceUser = srcUser
	}

	@Override
	public boolean equals(Object obj){
		Comment other = (Comment)obj
		return other.getSourceUser() == sourceUser &&
				other.getDate().equals(date) &&
				other.getDescription().equals(description)
				
	}
	
	


}
