package com.library

class Location {

	static belongsto = Library
	
	String country
	String city
	String street
	
	String latitude
	String longitude

    static constraints = {
		country(maxsize: 60,nullable:true)
		city(maxsize: 60, nullable:true)
		street(maxsize: 60, nullable:true)
		latitude(maxsize: 10, nullable:true)
		longitude(maxsize: 10, nullable:true)
    }
	
	String address(){
		def addr = []
		String address
		addr << this.street << this.city << this.country
		address = addr.join(',')
		return address
	}
	
	
}

