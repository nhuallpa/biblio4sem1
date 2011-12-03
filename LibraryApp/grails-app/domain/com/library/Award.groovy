package com.library

class Award {

	int score
	String detail
	String category
	
	static constraints = {
		score(nullable:true)
		detail(nullable:true)
		category(nullable:true)
	}
	
}
