package com.library

class Comment {
	
	String description
	Date date
	User sourceUser
	Book book
	Integer score
		

	static constraints = {
		description (blank : false, nullable: true)
		book(blank: false)
		date(nullable:true)
		score(nullable:true)
	}
	
	static belongsTo = [sourceUser:User,book:Book]
	
	void addUser(User srcUser){
		this.sourceUser = srcUser
	}

//	@Override
//	public boolean equals(Object obj){
//		Comment other = (Comment) obj
//		return this.id == other.id;
//				
//	}
	
	


}
