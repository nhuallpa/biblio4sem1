package com.library.android.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name = "";
	private Location address;
	private String email;
	private List<Comment> listOfComments;
	private List<Reservation> listOfReservations;
	private String id;
	private int score;
		
	public User(){
		init();
	}
	
	public User(String name, Location address, String email){
		this.name = name;
		this.address = address;
		this.email = email;
		init();
		
	}
	
	private void init(){
		this.listOfComments = new ArrayList<Comment>();
		this.listOfReservations = new ArrayList<Reservation>();
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void addComment(Book aBook, Comment aComment){
//		aComment.setUser(this);
		aComment.setSources(this,aBook);
		aBook.addComment(aComment);
		this.listOfComments.add(aComment);
	}
	
	public void addBookComment(Book aBook){
		
	}

	public String getName() {
		return name;
	}
	
	public void addLibraryComment(Library aLibrary){
		
	}
	
	public void addUserComment(User user){
		
	}
	
	public void lookSimilars(){
		
	}
	
	public void caregorizeBook(Book aBook, String aTag){
		
	}
	
	public void lookMyReservations(){
		
	}
	
	public void cancelReservation(Reservation aReservation){
		
	}
	
	public void addReservation(Reservation aReservation){
		this.listOfReservations.add(aReservation);
	}
	
	public void lookBookOnCategory(Category aCategory){
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}

	public List<Reservation> getListOfReservations() {
		return listOfReservations;
	}

	public void setListOfReservations(List<Reservation> listOfReservations) {
		this.listOfReservations = listOfReservations;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	

}
