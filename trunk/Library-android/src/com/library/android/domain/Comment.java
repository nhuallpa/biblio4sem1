package com.library.android.domain;

import java.util.Date;

import android.text.format.DateFormat;

public class Comment {
	
	private String description;
	private User user;
	private Date date;
	private float score;
	
	public Comment(String desc, User user){
		this.description = desc;
		this.date = new Date(DateFormat.YEAR, DateFormat.MONTH, DateFormat.DAY);
		this.user = user;
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

	
}
