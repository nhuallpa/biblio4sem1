package com.library.android.mock;

import java.util.ArrayList;
import java.util.List;

import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.User;

public class BooksMock {
	
	public static List<Book> getListBooks(){
		List<Book> list = new ArrayList<Book>();
		
		Book book1 = new Book(new Long(12345), "Thinking in Java", null);
		book1.addComment(new Comment("Feo libroo", UserMock.getUser()));
		Book book2 = new Book(new Long(6789), "Learning C", null);
		book2.addComment(new Comment("Bastante bueno", UserMock.getUser()));
		Book book3 = new Book(new Long(01234), "Visual Basic for Dummies", null);
		
		list.add(book1);
		list.add(book2);
		list.add(book3);
		
		return list;
	}

}
