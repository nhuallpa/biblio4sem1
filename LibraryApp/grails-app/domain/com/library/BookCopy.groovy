package com.library

class BookCopy {

	States state
	
	static belongsTo = [library:Library, bookMaster:Book]
	
    static constraints = {
		state(inList: States.list())
    }
}
