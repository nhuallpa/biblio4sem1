package com.library

class Award {

	int score
	String detail
	String category
	String info
	
	static constraints = {
		score(nullable:true)
		detail(nullable:true)
		category(nullable:true)
		info(nullable:true)
	}

}
