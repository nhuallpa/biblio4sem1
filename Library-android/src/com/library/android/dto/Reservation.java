package com.library.android.dto;


public class Reservation {
	
	private User user;
	private String dateReservation;
	private String book;
	private String bookId;
	private String state;
	private Library library;
	
	public Reservation(){}
	
//	public Reservation(User user, Book book, Library library){
//		this.user = user;
//		this.book = book;
//		this.library = library;
//	}
	
//	public Reservation(User user, Book book){
//		this.user = user;
//		this.book = book;
//	}
	
//	public boolean isDelivered(long reservaId){
//		return false;
//	}
//	
//	public void cancel(){
//		
//	}
	
	public void setBookId(String bookId){
		this.bookId = bookId;
	}
	
	public String getBookId(){
		return this.bookId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(String dateReservation) {
		this.dateReservation = dateReservation;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}
}
