package com.library

class Library {

	String libraryId
	String name
	byte[] photo
	String homepage
	String mail
	String phone

    static constraints = {
		libraryId(size: 3..20, unique: true)
		photo(nullable: true)
		homepage(url: true, nullable: true)
		mail(nullable: true)
		phone(nullable: true)
    }
}
