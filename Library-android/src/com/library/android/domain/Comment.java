package com.library.android.domain;

import java.util.Date;

import android.text.format.DateFormat;

public class Comment {
	
	private String description;
	private User user;
	private Date date;
	private float score;
	private Book bookSource;
	
	public Comment(String desc, Book book){
		this.bookSource = book;
		this.description = desc;
		this.date = new Date(DateFormat.YEAR, DateFormat.MONTH, DateFormat.DAY);
	}
	
	public Comment(String desc, Book book, float score){
		this.bookSource = book;
		this.description = desc;
		this.date = new Date(DateFormat.YEAR, DateFormat.MONTH, DateFormat.DAY);
		this.score = score;
	}
	
	public Comment(String desc, float score){
		this.score = score;
		this.description = desc;
		this.date = new Date(DateFormat.YEAR, DateFormat.MONTH, DateFormat.DAY);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}
	
	public Book getBookSource(){
		return bookSource;
	}

	public void setSources(User aUser, Book aBook) {
		this.bookSource = aBook;
		this.user = aUser;
		
	}

	
}
