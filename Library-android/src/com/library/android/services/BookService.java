package com.library.android.services;

import java.util.List;

import com.library.android.dto.Book;

public interface BookService {
	
	public List<Book> findBooks(String text);
	
	public List<Book> getTopBooks();

}
