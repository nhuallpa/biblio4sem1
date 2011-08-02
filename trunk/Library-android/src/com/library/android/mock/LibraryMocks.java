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
	private Book book6;
	private Book book7;
	private Book book8;
	private Book book9;
	private Book book10;
	private Book book11;
	
	
	private Comment comment1;
	private Comment comment2;
	private Comment comment3;
	private Comment comment4;
	private Comment comment5;
	private Comment comment6;
	private Comment comment7;
	private Comment comment8;
	private Comment comment9;
	
	private User gonza;
	private User nestor;
	private User ariel;
	
	private List<Book> bookList = new ArrayList<Book>();
		
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
		book6 = new Book(new Long(7461), "The Ruby Programming", "David Flanagan", ConstantsMock.book6_picture);
		book7 = new Book(new Long(8714), "Hello, Android", "Ed Burnette", ConstantsMock.book7_picture);
		book8 = new Book(new Long(2345), "BlackBerry for Dummies", "Robert Kao", ConstantsMock.book8_picture);
		book9 = new Book(new Long(7671), "iPad 2", "J D Biersdorfer", ConstantsMock.book9_picture);
		book10 = new Book(new Long(2345), "iPhone 4", "David Mark", ConstantsMock.book10_picture);
		book11 = new Book(new Long(2345), "Pro C# and .NET", "Andrew W. Troelsen", ConstantsMock.book11_picture);
		
		
		comment1 = new Comment("Feo libroo", 2);
		comment2 = new Comment("Bastante bueno",4);
		comment3 = new Comment("Zafa", 2);
		comment4 = new Comment("Esta bueno", 3);
		comment5 = new Comment("Me gusta todo!", 5);
		comment6 = new Comment("Se podrian haber esmerado un poco mas...", 2);
		comment7 = new Comment("Ni bueno ni malo..", 3);
		comment8 = new Comment("No me gusta", 1);
		comment9 = new Comment("Malo", 2);
		
		init();
	}
	
	public User getUser(){
		return gonza;	
	}
	
	private void init(){
		
		//gonza
		gonza = new User();
		gonza.setName("Gonza");
		gonza.setEmail("gonza@gmail.com");
		
		gonza.addComment(book1, comment1);
		gonza.addComment(book2, comment2);
		gonza.addComment(book2, comment3);
		gonza.addComment(book3, comment1);
		gonza.addComment(book4, comment4);
		gonza.addComment(book5, comment4);
		gonza.addComment(book5, comment2);
		gonza.addComment(book5, comment3);
		gonza.addComment(book5, comment1);
		gonza.addComment(book5, comment3);
		gonza.addComment(book5, comment2);
		gonza.addComment(book5, comment7);
		gonza.addComment(book5, comment4);
		gonza.addComment(book5, comment2);
		
		gonza.makeReservation(book2);
		gonza.makeReservation(book5);
		
		
		//nestor
		nestor = new User();
		nestor.setName("Nestor");
		nestor.setEmail("nestor@gmail.com");
		
		nestor.addComment(book11, comment5);
		nestor.addComment(book3, comment8);
		nestor.addComment(book10, comment6);
		nestor.addComment(book5, comment1);
		nestor.addComment(book7, comment4);
		nestor.addComment(book11, comment9);
		nestor.addComment(book2, comment8);
		nestor.addComment(book6, comment2);
		nestor.addComment(book5, comment3);
		nestor.addComment(book9, comment8);
		
		nestor.makeReservation(book10);
		nestor.makeReservation(book3);
		nestor.makeReservation(book6);
		
		
		//ariel
		ariel = new User();
		ariel.setName("Ariel");
		ariel.setEmail("ariel@gmail.com");
		
		ariel.addComment(book1, comment2);
		ariel.addComment(book9, comment8);
		ariel.addComment(book5, comment6);
		ariel.addComment(book4, comment3);
		ariel.addComment(book3, comment6);
		ariel.addComment(book7, comment9);
		ariel.addComment(book8, comment4);
		ariel.addComment(book2, comment3);
		ariel.addComment(book7, comment1);
		
		ariel.makeReservation(book7);
		ariel.makeReservation(book9);
		
		bookList.add(book11);
		bookList.add(book2);
		bookList.add(book10);
		bookList.add(book4);
		bookList.add(book8);
		bookList.add(book9);
		bookList.add(book3);
		bookList.add(book6);
		
	}
	
	public List<Book> getTopBooks(){
//		
//		if(bookList.size() == 0){
//			bookList.add(book11);
//			bookList.add(book2);
//			bookList.add(book10);
//			bookList.add(book4);
//			bookList.add(book8);
//			bookList.add(book9);
//			bookList.add(book3);
//			bookList.add(book6);
//		}
		
		return bookList;
	}
	

		 


}
