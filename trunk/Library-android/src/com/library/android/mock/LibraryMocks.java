package com.library.android.mock;

import java.util.ArrayList;
import java.util.List;

import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.User;

public class LibraryMocks {
	

	
	private static Book book1 = new Book(new Long(12345), "Thinking in Java", "Autor 1", ConstantsMock.book1_picture);
	private static Book book2 = new Book(new Long(6789),"Objective-C", "Autor 2",  ConstantsMock.book2_picture);
	private static Book book3 = new Book(new Long(7894), "It","Autor 3", ConstantsMock.book3_picture);
	private static Book book4 = new Book(new Long(4656), "Grails in Action","Dierk Koenig", ConstantsMock.book4_picture);
	private static Book book5 = new Book(new Long(6547), "Groovy in Action", "Andrew Glover", ConstantsMock.book5_picture);
	
	private static Comment comment1 = new Comment("Feo libroo", 2);
	private static Comment comment2 = new Comment("Bastante bueno",4);
	private static Comment comment3 = new Comment("Zafa", 2);
	private static Comment comment4 = new Comment("Esta bueno", 3);
	
	public static Book[] bookList = {book1, book2, book3};
	
	public static User getUser(){
		User user = new User();
		user.setName("Pepe");
		
//		Book book1 = new Book(new Long(12345), "Thinking in Java", null);
		user.addComment(book1, comment1);
//		Book book2 = new Book(new Long(6789), "Learning C", null);
		user.addComment(book2, comment2);
		user.addComment(book2, comment3);
		user.addComment(book3, comment1);
		user.addComment(book4, comment4);
		user.addComment(book5, comment4);
				
		return user;
		
	}
	
	public static List<Book> getAllBooks(){
		List<Book> list = new ArrayList<Book>();
		User user = getUser();
		list.add(book1);
		list.add(book2);
		list.add(book3);
		list.add(book4);
		list.add(book5);
		
		return list;
	}
	
 


}
