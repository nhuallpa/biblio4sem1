package com.library

class Location {

	static belongsto = Library
	
	String country
	String city
	String street
	Integer number
	
	String latitude
	String longitude

    static constraints = {
		country(maxsize: 60,nullable:true)
		city(maxsize: 60, nullable:true)
		street(maxsize: 60, nullable:true)
		number(maxsize: 20, nullable:true)
		latitude(maxsize: 10, nullable:true)
		longitude(maxsize: 10, nullable:true)
    }
}
