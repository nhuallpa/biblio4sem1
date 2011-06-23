package com.library.android.mock;

import java.util.ArrayList;
import java.util.List;

import com.library.android.domain.Book;

public class BooksMock {
	
	public static List<Book> getListBooks(){
		List<Book> list = new ArrayList<Book>();
		
		Book book1 = new Book(new Long(12345), "Thinking in Java", null);
		Book book2 = new Book(new Long(6789), "Learning C", null);
		Book book3 = new Book(new Long(01234), "Visual Basic for Dummies", null);
		
		list.add(book1);
		list.add(book2);
		list.add(book3);
		
		return list;
	}

}
