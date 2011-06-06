package com.library.android.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
	
	private List<Book> listOfBooksId;
	private String nameCategory;
	
	public Category(String name){
		this.nameCategory = name;
		this.listOfBooksId = new ArrayList<Book>();
	}

	public void addBookToCategory(Book aBook){
		this.listOfBooksId.add(aBook);
	}
	
	public void lookBooksOnCategory(){
		
	}
	
	public List<Book> getListOfBooksId() {
		return listOfBooksId;
	}

	public void setListOfBooksId(List<Book> listOfBooksId) {
		this.listOfBooksId = listOfBooksId;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	
	

}
