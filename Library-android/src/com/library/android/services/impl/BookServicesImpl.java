package com.library.android.services.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.States;
import com.library.android.domain.User;
import com.library.android.services.BookService;
import com.library.android.services.ConfigWS;
import com.library.android.utils.Utils;

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
	
	public void toComment(String bookId, String aComment, String score){
		
		User user = ConfigurationManager.getInstance(context).getCurrentUser();
		String url = ConfigWS.TO_COMMENT_BOOK + "?bookId=" + bookId + "&userId=" + user.getId();
		try{
			JSONObject json = new JSONObject();
			HttpPost request = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			aComment = aComment.concat(" --" + Constants.COMMENT_EXTRA + "--");
			json.put("text", aComment);
	        json.put("rating", score);
	        
            StringEntity se = new StringEntity(json.toString());  
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);
            
            client.execute(request);
            
            

		}catch (JSONException e){} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	

	
	
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
				

			    JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
			    
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

	public void toReserveBook(String bookId){
//		book.reserveMe();
		User user = ConfigurationManager.getInstance(context).getCurrentUser();
		String url = ConfigWS.TO_RESERVE_BOOK + "?bookId=" + bookId + "&userId=" + user.getId();
		boolean result = false;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			result = request.equals("OK"); 

			con.disconnect();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
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

				JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
			    
			    for(int i = 0; i < array.length(); i++){
			    	JSONObject obj = array.getJSONObject(i);
			    	Book aBook = new Book(Long.valueOf(obj.getString("isbn")),
			    						obj.getString("name"),
			    						obj.getString("author"),
			    						null,
			    						obj.getString("description"));
			    	aBook.setBookId(Long.valueOf(obj.getString("id")));
			    	aBook.setListOfComments(findCommentsByBook(obj.getString("id")));
			    	
			    	if(obj.getString("state").equals(States.RESERVED.toString())){
			    		aBook.reserveMe();
			    	}
			    	
			    	
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
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){

				JSONObject obj = new JSONObject(Utils.parseLine(con.getInputStream()));
			    
				aBook = new Book(Long.valueOf(obj.getString("isbn")),
			    						obj.getString("name"),
			    						obj.getString("author"),
			    						null,
			    						obj.getString("description"));
			    	aBook.setBookId(Long.valueOf(bookId));
			    	aBook.setListOfComments(findCommentsByBook(bookId));
			    	if(obj.getString("state").equals(States.RESERVED.toString())){
			    		aBook.reserveMe();
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
		
		return aBook;
	}


}
