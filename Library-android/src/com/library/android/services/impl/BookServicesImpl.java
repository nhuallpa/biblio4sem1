package com.library.android.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.library.android.config.ConfigurationManager;
import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.User;
import com.library.android.mock.LibraryMocks;
import com.library.android.services.BookService;
import com.library.android.services.ConfigWS;

public class BookServicesImpl implements BookService {
	
	private static String url;
	
	private static BookServicesImpl instance;
	private static Context context;
	
	private BookServicesImpl(){
		ConfigWS config = ConfigWS.getInstance();
//		url = ConfigWS.WS_SEARCH + "mail=" + config.getUser();
	}
	
	public static BookServicesImpl getInstance(Context ctx){
		if(instance == null){
			instance = new BookServicesImpl();
		}
		context = ctx;
		return instance;
	}
	
	@Override
	public List<Book> findBook(String text){
		
//		String urlFinal = url + "&bookId=" + text;
//		String request = null;
//		
//		JSONArray array = null;
//		JSONObject bookJs = null;
//		Book book = new Book();
//		try {
//			URL u = new URL(urlFinal);
//			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
//			con.setDoInput(true);
//			con.setRequestMethod("GET");
//			con.connect();
//			request = con.getResponseMessage();
//			if(request.equals("OK")){
//				
//				InputStream inputStream = con.getInputStream();
//					
//				// Parse it line by line
//			    BufferedReader bufferedReader = new BufferedReader(
//			                new InputStreamReader(inputStream));
//			    StringBuffer sb = new StringBuffer();
//
//			    String str = "";
//			    while ((str = bufferedReader.readLine()) != null) {
//			     sb.append(str);
//			    }
//			    array = new JSONArray(sb.toString());
//				bookJs = array.getJSONObject(1);
//				book.setBookId(bookJs.getLong("bookId"));
//				book.setTitle(bookJs.getString("title"));
//					
//				con.disconnect();
//			}
//			//array = new JSONArray(request);
//			
//				
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//					
				
		List<Book> booksFounded = null;//LibraryServiceImpl.getInstance().booksFounded(BooksMock.getListBooks(), text);
		
//		return book;
		return booksFounded;
	}
	
	public void addBook(String aRequest){
		
	}
	
	public boolean validate(Long bookId){
		
		return false;
	}
	
	public boolean toComment(String bookId, String aComment, String score){
		
		User user = ConfigurationManager.getInstance(context).getCurrentUser();
//		user.addComment(book, aComment);
		String url = ConfigWS.TO_COMMENT_BOOK + "?bookId=" + bookId + "&userId=" + user.getId() + 
					"&text=" + aComment + "&rating=" + score;
		
		boolean result = false;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.connect();
			String request = con.getResponseMessage();
			result = request.equals("OK"); 

			//array = new JSONArray(request);
			con.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return result;
	}
	
//	public void toReserveBook(Long bookId, Reservation aReservation){
//		
//	}
	
//	public List<Book> getAllBooks(){
//		List<Book> books = new ArrayList<Book>();
//		String url = ConfigWS.FIND_ALL_BOOKS;
//		try{
//			URL u = new URL(url);
//			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
//			con.setDoInput(true);
//			con.setRequestMethod("GET");
//			con.connect();
//			String request = con.getResponseMessage();
//			if(request.equals("OK")){
//				
//				
////				// Parse it line by line
////			    BufferedReader bufferedReader = new BufferedReader(
////			                new InputStreamReader(inputStream));
////			    StringBuffer sb = new StringBuffer();
////	
////			    String str = "";
////			    while ((str = bufferedReader.readLine()) != null) {
////			     sb.append(str);
////			    }
//			    JSONArray array = new JSONArray(parseLine(con.getInputStream()));
//			    
//			    for(int i = 0; i < array.length(); i++){
//			    	JSONObject obj = array.getJSONObject(i);
//			    	Book aBook = new Book(Long.valueOf(obj.getString("isbn")),
//			    						obj.getString("name"),
//			    						obj.getString("author"),
//			    						null,
//			    						obj.getString("description"));
//			    	aBook.setBookId(Long.valueOf(obj.getString("id")));
//			    	aBook.setListOfComments(findCommentsByBook(obj.getString("id")));
//			    	
//			    	books.add(aBook);
//			    }
//					
//				
//			}
//			//array = new JSONArray(request);
//			con.disconnect();
//			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return books;
//	}
	
	private List<Comment> findCommentsByBook(String bookId) {
		List<Comment> comments = new ArrayList<Comment>();
		
		String url = ConfigWS.FIND_BOOK_COMMENTS + bookId;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				

			    JSONArray array = new JSONArray(parseLine(con.getInputStream()));
			    
			    for(int i = 0; i < array.length(); i++){
			    	JSONObject obj = array.getJSONObject(i);
			    	Comment comment = new Comment();
			    	comment.setDescription(obj.getString("description"));
			    	comment.setScore(Float.valueOf(obj.getString("score")));
			    	User aUser = new User();
			    	aUser.setName(obj.getJSONObject("user").getString("name"));
			    	aUser.setId(obj.getJSONObject("user").getString("id"));
			    	Book aBook = new Book();
			    	aBook.setBookId(Long.valueOf(bookId));
			    	aBook.setTitle(obj.getJSONObject("book").getString("name"));
			    	aBook.setDescription(obj.getJSONObject("book").getString("description"));
			    	
			    	
			    	comment.setSources(aUser , aBook);
			    	comments.add(comment);
			    }
					
				Log.d("Find comments by Book: ", "size: " + comments.size());
			}
			//array = new JSONArray(request);
			con.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	private String parseLine(InputStream is){
		// Parse it line by line
	    BufferedReader bufferedReader = new BufferedReader(
	                new InputStreamReader(is));
	    StringBuffer sb = new StringBuffer();

	    String str = "";
	    try {
			while ((str = bufferedReader.readLine()) != null) {
			 sb.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return sb.toString();
	}

	public void toReserveBook(Book book){
		book.reserveMe();
	}

	@Override
	public List<Book> getTopBooks() {
		List<Book> books = new ArrayList<Book>();
		String url = ConfigWS.FIND_TOP_BOOKS;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){

				JSONArray array = new JSONArray(parseLine(con.getInputStream()));
			    
			    for(int i = 0; i < array.length(); i++){
			    	JSONObject obj = array.getJSONObject(i);
			    	Book aBook = new Book(Long.valueOf(obj.getString("isbn")),
			    						obj.getString("name"),
			    						obj.getString("author"),
			    						null,
			    						obj.getString("description"));
			    	aBook.setBookId(Long.valueOf(obj.getString("id")));
			    	aBook.setListOfComments(findCommentsByBook(obj.getString("id")));
			    	
			    	books.add(aBook);
			    }
					
				
			}
			con.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return books;
		
		
//		return LibraryMocks.getInstance().getTopBooks();
	}
	
	public Book getBookById(String bookId){
		Book aBook = null;

		String url = ConfigWS.FIND_BOOK + bookId;
//		List<Book> books = LibraryMocks.getInstance().getAllBooks();
//		Iterator<Book> itBook = books.iterator();
//		boolean founded = false;
//		while(itBook.hasNext() && !founded){
//			Book temp = itBook.next();
//			founded = temp.getISBN().equals(isbn); 
//			if(founded){
//				aBook = temp;
//			}
//		}
		
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){

				JSONObject obj = new JSONObject(parseLine(con.getInputStream()));
			    
				aBook = new Book(Long.valueOf(obj.getString("isbn")),
			    						obj.getString("name"),
			    						obj.getString("author"),
			    						null,
			    						obj.getString("description"));
			    	aBook.setBookId(Long.valueOf(bookId));
			    	aBook.setListOfComments(findCommentsByBook(bookId));
			    	
//			    	books.add(aBook);
			    
					
				
			}
			con.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return aBook;
	}


}
