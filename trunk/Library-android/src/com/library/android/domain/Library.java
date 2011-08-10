package com.library.android.domain;

import java.util.ArrayList;
import java.util.List;

public class Library {
	
	private List<Book> books;
	private Location location;
	private List<Reservation> listOfReservations;
	private List<Comment> listOfComments;
	private String name;
	
	public Library(String name, Location location){
		this.name = name;
		this.location = location;
		this.listOfComments = new ArrayList<Comment>();
		this.listOfReservations = new ArrayList<Reservation>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public void isSitueted(Location location){
		
	}
			
	public void addBook(Book aBook){
		this.books.add(aBook);
	}
	
	public void deleteBook(Book aBook){
		this.books.remove(aBook);
	}
	
	public void getScore(){
		
	}
	
	public void addComment(Comment comment){
		this.listOfComments.add(comment);
	}
	
	public void addReservation(Reservation reservation){
		this.listOfReservations.add(reservation);
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Reservation> getListOfReservations() {
		return listOfReservations;
	}

	public void setListOfReservations(List<Reservation> listOfReservations) {
		this.listOfReservations = listOfReservations;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}

	public Location getLocation() {
		return location;
	}
	
	

}
