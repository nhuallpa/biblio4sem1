package com.library.android.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private String title;
	private String subject;
	private Long ISBN;
	private List<Comment> listOfComments;
	private List<String> tags;
	private Library library;
	
	public Book(Long ISBN){
		this.ISBN = ISBN;
		this.listOfComments = new ArrayList<Comment>();
		this.tags = new ArrayList<String>();
	}
	
	public int getScore(){
		return 0;
	}
	
	public void addComment(Comment aComment){
		this.listOfComments.add(aComment);
	}
	
	public List<Book> similarsToMe(){
		return null;
	}

	public String getTitle() {
		return title;
	}
	
	public void reserveMe(){
		
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
	
	
	
	
}
