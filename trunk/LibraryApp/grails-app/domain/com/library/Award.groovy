package com.library

class Award {

	int score
	String detail
	
	static constraints = {
		score(nullable:true)
		detail(nullable:true)
	}
	
}
