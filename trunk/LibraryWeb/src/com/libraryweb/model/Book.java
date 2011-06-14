package com.libraryweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Book {

	public static final String NAME = "Book";
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private long bookId;
	
	@Persistent
	private String title;
	
	@Persistent
	private String state;
	
	@Persistent
	private List<String> listOfComments;
	
	public Book(){
		listOfComments = new ArrayList<String>();
	}
	
	public Book(String title, long bookId){
		
		this.title = title;
		this.bookId = bookId;
	}

	public List<String> getComments(){
		return listOfComments;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	
	
}
