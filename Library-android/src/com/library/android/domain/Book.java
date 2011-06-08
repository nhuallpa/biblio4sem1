package com.library.android.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private Long bookId;
	private String title;
	private String subject;
	private Long ISBN;
	private List<Comment> listOfComments;
	private List<String> tags;
	private Library library;
	
	public Book(Long ISBN, Library lib){
		this.ISBN = ISBN;
		this.library = lib;
		this.listOfComments = new ArrayList<Comment>();
		this.tags = new ArrayList<String>();
	}
	
	public float getScore(){
		float score = 0;
		for(Comment aComment : listOfComments){
			score = score + aComment.getScore();
		}
		return score;
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
	
	public void reserveMe(User user){
		
		Reservation reserved = new Reservation(user, this, library);
		library.addReservation(reserved);
	}
	
	public void categorizeMe(String tag){
		this.tags.add(tag);
	}

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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

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
	
	
	
	
}