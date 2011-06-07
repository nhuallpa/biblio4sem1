package com.library.android.domain;

import java.util.Date;

public class Reservation {
	
	private User user;
	private Date dateReservation;
	private Book book;
	private String state;
	private Library library;
	
	public Reservation(User user, Book book, Library library){
		this.user = user;
		this.book = book;
		this.library = library;
	}
	
	public boolean isReserved(long reservaId){
		boolean encontrado = false;
		
		for(int i = 0; i < library.getListOfReservations().size() && !encontrado; i++){
			encontrado = library.getListOfReservations().get(i).equals(this);
		}
		
		return encontrado;
	}
	
	public boolean isDelivered(long reservaId){
		return false;
	}
	
	public void cancel(){
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
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
