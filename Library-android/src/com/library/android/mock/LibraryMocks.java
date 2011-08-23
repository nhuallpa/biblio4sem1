package com.library.android.mock;

import java.util.ArrayList;
import java.util.List;

import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.Library;
import com.library.android.domain.Location;
import com.library.android.domain.User;

public class LibraryMocks {
	
	private Book book1; private String description1;
	private Book book2; private String description2;
	private Book book3; private String description3;
	private Book book4; private String description4;
	private Book book5; private String description5;
	private Book book6; private String description6;
	private Book book7; private String description7;
	private Book book8; private String description8;
	private Book book9; private String description9;
	private Book book10; private String description10;
	private Book book11; private String description11;
	
	private String textDesc = "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper.";
	
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
	
	private Library floridaLibrary;
	private Library floresLibrary;
	private Library liniersLibrary;
	
	private List<Book> topBooks = new ArrayList<Book>();
	private List<Book> allBooks = new ArrayList<Book>();
	private List<Library> libraries = new ArrayList<Library>();

		
	private static LibraryMocks instance;
	
	public static LibraryMocks getInstance(){
		if(instance == null){
			instance = new LibraryMocks();
		}
		return instance;
	}
	
	
	private LibraryMocks(){
//		floridaLibrary = new Library("Florida Library",new Location("Florida 600", -34.60107, -58.37535));
//		floresLibrary = new Library("Flores Library", new Location("Av Rivadavia 6800", -34.62845, -58.46118));
//		liniersLibrary = new Library("Liniers Library", new Location("Av Rivadavia 11684", -34.63936, -58.52867));
		
		description1 = textDesc;
		description2 = textDesc;
		description3 = textDesc;
		description4 = textDesc;
		description5 = textDesc;
		description6 = textDesc;
		description7 = textDesc;
		description8 = textDesc;
		description9 = textDesc;
		description10 = textDesc;
		description11 = textDesc;
		
//		book1 = new Book(new Long(1234), "Thinking in Java", "Autor 1", ConstantsMock.book1_picture, description1);
//		book2 = new Book(new Long(6789),"Objective-C", "Autor 2",  ConstantsMock.book2_picture, description2);
//		book3 = new Book(new Long(7894), "It","Autor 3", ConstantsMock.book3_picture, description3);
//		book4 = new Book(new Long(4656), "Grails in Action","Dierk Koenig", ConstantsMock.book4_picture, description4);
//		book5 = new Book(new Long(6547), "Groovy in Action", "Andrew Glover", ConstantsMock.book5_picture, description5);
//		book6 = new Book(new Long(7461), "The Ruby Programming", "David Flanagan", ConstantsMock.book6_picture, description6);
//		book7 = new Book(new Long(8714), "Hello, Android", "Ed Burnette", ConstantsMock.book7_picture, description7);
//		book8 = new Book(new Long(1092), "BlackBerry for Dummies", "Robert Kao", ConstantsMock.book8_picture, description8);
//		book9 = new Book(new Long(7671), "iPad 2", "J D Biersdorfer", ConstantsMock.book9_picture, description9);
//		book10 = new Book(new Long(2345), "iPhone 4", "David Mark", ConstantsMock.book10_picture, description10);
//		book11 = new Book(new Long(5786), "Pro C# and .NET", "Andrew W. Troelsen", ConstantsMock.book11_picture, description11);
//		
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
		
		libraries.add(floridaLibrary);
		libraries.add(floresLibrary);
		libraries.add(liniersLibrary);
		
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
		
		//topBooks
		topBooks.add(book11);
		topBooks.add(book2);
		topBooks.add(book10);
		topBooks.add(book4);
		topBooks.add(book8);
		topBooks.add(book9);
		topBooks.add(book3);
		topBooks.add(book6);
		
		//allBooks
		allBooks.add(book1);
		allBooks.add(book2);
		allBooks.add(book3);
		allBooks.add(book4);
		allBooks.add(book5);
		allBooks.add(book6);
		allBooks.add(book7);
		allBooks.add(book8);
		allBooks.add(book9);
		allBooks.add(book10);
		allBooks.add(book11);
		
		
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
		
		return topBooks;
	}
	
	public List<Book> getAllBooks(){
		return allBooks;
	}
		 
	public List<Library> getLibraries(){
		return this.libraries;
	}

}
