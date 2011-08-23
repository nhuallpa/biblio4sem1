package com.library.android.domain;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class Book implements Comparable<Book>{

	private Long bookId;
	private String author;
	private String title;
	private String subject;
	private Long ISBN;
	private List<Comment> listOfComments;
	private List<Library> librarys;
	private Bitmap picture;
	private String description;
	private States state;
	
	public Book(Long ISBN, String title){
		this.ISBN = ISBN;
		
		this.title = title;
		init();
	}
	
	public Book(Long ISBN, String title, String author, Bitmap picture){
		this.ISBN = ISBN;
		this.picture = picture;
		this.title = title;
		this.author = author;
		init();
	}
	
	public Book(Long ISBN, String title, String author, Bitmap picture, String description){
		this.ISBN = ISBN;
		this.picture = picture;
		this.title = title;
		this.author = author;
		this.description = description;
		init();
	}
	
	public Book(Long ISBN, String title, Bitmap picture){
		this.ISBN = ISBN;
//		this.library = lib;
		this.title = title;
		this.picture = picture;
		init();
	}
	
	public List<Library> getLibrarys(){
		return this.librarys;
	}
	
	public void addLibrary(Library lib){
		this.librarys.add(lib);
	}
	
	public States getState(){
		return this.state;
	}
	
	public void reserveMe(){
		this.state = States.RESERVED;
	}
	
	public void cancelReservation(){
		this.state = States.AVAILABLE;
	}
	
	public boolean isReserved(){
		return (this.state == States.RESERVED);
	}
		
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book(){
		init();
	}
		
	private void init(){
		this.listOfComments = new ArrayList<Comment>();
//		this.tags = new ArrayList<String>();
		this.librarys = new ArrayList<Library>();
		this.state = States.AVAILABLE;
	}
	
	public double getScore(){
		double score = new Float(0);
		for(Comment aComment : listOfComments){
			score = score + aComment.getScore();
		}
		score = score / listOfComments.size();
		return score;
	}
	
	public void setPicture(Bitmap source){
		this.picture = source;
	}
	
	public Bitmap getPicture(){
		return this.picture;
	}
	
	public void addComment(Comment aComment){
		this.listOfComments.add(aComment);
	}
	
	//3 tags iguales --> similar
	public List<Book> similarsToMe(){
		return null;
	}

	public String getTitle() {
		return title;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void reserveMe(User user){
		
//		Reservation reserved = new Reservation(user, this, library);
//		if(library != null){
//			library.addReservation(reserved);
//		}
		reserveMe();
	}
	
//	public void categorizeMe(String tag){
//		this.tags.add(tag);
//	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}

//	public List<String> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<String> tags) {
//		this.tags = tags;
//	}


	public Long getISBN() {
		return ISBN;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
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
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}

	@Override
	public int compareTo(Book another) {
	    final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;
	    int compare = 0;
	    if(this.equals(another)){
	    	compare = EQUAL;
	    }
		if(this.bookId < another.getBookId()){
			compare = BEFORE;
		}
		if(this.bookId > another.getBookId()){
			compare = AFTER;
		}
		return compare; 
		
	}
	
	
	
	
}
