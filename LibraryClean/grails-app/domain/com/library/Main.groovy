package com.library

public class Main {

    static constraints = {
    }
	
	public static void main(String[] args) {

		User aUser
		List<Book> listOfBooks = new ArrayList<Book>()
		Book aBook
		Location location
		Library aLibrary
				
		location = new Location(country: "countryTest", street: "streetTest")
		aUser = new User(name: "Ariel", location: location)
		aLibrary = new Library(libraryId: "BA_Ateneo", name: "El Ateneo")
		
		aBook = new Book(title:"C",ISBN:"1",state:"Available",library:aLibrary)
		aUser.makeReservation aBook
		
	}
	
}
