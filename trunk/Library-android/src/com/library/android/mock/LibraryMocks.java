package com.library.android.mock;

import java.util.ArrayList;
import java.util.List;

import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.User;

public class LibraryMocks {
	

	
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private Book book5;
	
	private Comment comment1;
	private Comment comment2;
	private Comment comment3;
	private Comment comment4;
	
	private User user;
	
	private List<Book> bookList = new ArrayList<Book>();
	
//	public static Book[] bookList = {book1, book2, book3};
	
	private static LibraryMocks instance;
	public static LibraryMocks getInstance(){
		if(instance == null){
			instance = new LibraryMocks();
		}
		return instance;
	}
	
	
	private LibraryMocks(){
		book1 = new Book(new Long(12345), "Thinking in Java", "Autor 1", ConstantsMock.book1_picture);
		book2 = new Book(new Long(6789),"Objective-C", "Autor 2",  ConstantsMock.book2_picture);
		book3 = new Book(new Long(7894), "It","Autor 3", ConstantsMock.book3_picture);
		book4 = new Book(new Long(4656), "Grails in Action","Dierk Koenig", ConstantsMock.book4_picture);
		book5 = new Book(new Long(6547), "Groovy in Action", "Andrew Glover", ConstantsMock.book5_picture);
		
		comment1 = new Comment("Feo libroo", 2);
		comment2 = new Comment("Bastante bueno",4);
		comment3 = new Comment("Zafa", 2);
		comment4 = new Comment("Esta bueno", 3);
		
		init();
	}
	
	public User getUser(){
		return user;	
	}
	
	private void init(){
		user = new User();
		user.setName("Pepe");

		user.addComment(book1, comment1);
		user.addComment(book2, comment2);
		user.addComment(book2, comment3);
		user.addComment(book3, comment1);
		user.addComment(book4, comment4);
		user.addComment(book5, comment4);
		user.addComment(book5, comment2);
		user.addComment(book5, comment3);
	}
	
	public List<Book> getAllBooks(){
		
		if(bookList.size() == 0){
			bookList.add(book1);
			bookList.add(book2);
			bookList.add(book3);
			bookList.add(book4);
			bookList.add(book5);
		}
		
		return bookList;
	}
	
 


}
