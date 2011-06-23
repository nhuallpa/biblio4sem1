package com.library.services;

import java.util.List;

import com.library.android.domain.Book;

public interface LibraryService {
	
	public List<Book> booksFounded(List<Book> list, String text);
}
