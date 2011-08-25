package com.library.android.services.impl;

import java.io.IOException;
import java.io.InputStream;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.dto.Book;
import com.library.android.dto.Comment;
import com.library.android.dto.States;
import com.library.android.dto.User;
import com.library.android.services.BookService;
import com.library.android.services.ConfigWS;
import com.library.android.utils.Utils;

public class BookServicesImpl implements BookService {
	
	
	private static BookServicesImpl instance;
	private static Context context;
	
	private BookServicesImpl(){}
	
	public static BookServicesImpl getInstance(Context ctx){
		if(instance == null){
			instance = new BookServicesImpl();
		}
		context = ctx;
		return instance;
	}
	
	@Override
	public List<Book> findBooks(String text){
		
		List<Book> booksFounded = new ArrayList<Book>();
		String url = ConfigWS.SEARCH_BOOK;

		try{
			
			JSONObject json = new JSONObject();
			HttpPost request = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			json.put("q", text);
	        	        
            StringEntity se = new StringEntity(json.toString());  
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);
           
            HttpResponse httpResponse = client.execute(request);
            
            JSONArray array = new JSONArray(Utils.parseLine(httpResponse.getEntity().getContent()));
            
		    for(int i = 0; i < array.length(); i++){
		    	JSONObject obj = array.getJSONObject(i);
		    	Book book = convertToBook(obj);
		    	booksFounded.add(book);
		    }
            
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return booksFounded;
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
		try {
			DoGetEntity connection = new DoGetEntity(url);
			if(connection.getResponse().equals("OK")){
				

			    JSONArray array = new JSONArray(connection.parseInputStream());
			    
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
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return comments;
	}

	public boolean toReserveBook(String bookId, String libraryId){
		User user = ConfigurationManager.getInstance(context).getCurrentUser();
		String url = ConfigWS.TO_RESERVE_BOOK + "?bookId=" + bookId + "&userId=" + user.getId() + "&libraryId=" + libraryId;
		boolean result = false;
		try{

			result = doPost(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	public List<Book> getTopBooks() {
		List<Book> books = new ArrayList<Book>();
		String url = ConfigWS.FIND_TOP_BOOKS;
		
			try {
					DoGetEntity connection = new DoGetEntity(url);
					if(connection.getResponse().equals("OK")){
	
						JSONArray array = new JSONArray(connection.parseInputStream());
					    
					    for(int i = 0; i < array.length(); i++){
					    	JSONObject obj = array.getJSONObject(i);
					    	Book aBook = convertToBook(obj);
		  	
					    	books.add(aBook);
					    }
							
						
					}
					connection.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		return books;
	}
	

	public Bitmap getPicture(String bookName){
		Bitmap bitmap = null;
		
		String url = ConfigWS.PICTURE_BOOK;
		try{
			
			JSONObject json = new JSONObject();
			HttpPost request = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			json.put("name", bookName);
	        	        
            StringEntity se = new StringEntity(json.toString());  
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);
           
            HttpResponse httpResponse = client.execute(request);
			
            InputStream inputStream = httpResponse.getEntity().getContent();
            
            bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream), 50, 70, true);
            
           
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		
		return bitmap;
	}
	
	public Book getBookById(String bookId){
		Book aBook = null;
		String url = ConfigWS.FIND_BOOK + bookId;
		try {
			DoGetEntity connection = new DoGetEntity(url);
			if(connection.getResponse().equals("OK")){

				JSONObject obj = new JSONObject(connection.parseInputStream());
				aBook = new Book(Long.valueOf(obj.getString("isbn")),
						obj.getString("name"),
						obj.getString("author"),
						null,
						obj.getString("description"));
				aBook.setBookId(Long.valueOf(obj.getString("id")));
				aBook.setListOfComments(findCommentsByBook(obj.getString("id")));

				if(obj.getString("state").equals(States.RESERVED.toString())){
					aBook.reserveMe();
				}
				
			}
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return aBook;
	}
	
	public boolean cancelReserve(String bookId){
		boolean result = false;
		User user = ConfigurationManager.getInstance(context).getCurrentUser();
		String url = ConfigWS.CANCEL_RESERVE + "?userId=" + user.getId() + "&bookId=" + bookId;
		try{
			
			result = doPost(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	public boolean deleteMyComment(String commentId, String bookId){
		User user = ConfigurationManager.getInstance(context).getCurrentUser();
		String url = ConfigWS.DELETE_COMMENT + "?userId=" + user.getId() + "&commentId=" + commentId + "&bookId=" + bookId;
		boolean result = false;
		try{
			
			result = doPost(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	private Book convertToBook(JSONObject obj){
		Book aBook = null;
		try{
			aBook = new Book(Long.valueOf(obj.getString("isbn")),
								obj.getString("name"),
								obj.getString("author"),
								null,
								obj.getString("description"));
			aBook.setBookId(Long.valueOf(obj.getString("id")));
			aBook.setListOfComments(findCommentsByBook(obj.getString("id")));

			if(obj.getString("state").equals(States.RESERVED.toString())){
				aBook.reserveMe();
			}

		}catch(JSONException e){
			Log.e("Convert to Book", e.getMessage());
		}
		
		return aBook;
	}
	
	private boolean doPost(String url) throws IOException{
		boolean result = false;
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection ();
		con.setDoInput(true);
		con.connect();
		String response = con.getResponseMessage();
		result = response.equals("OK"); 

		con.disconnect();
		
		return result;
	}
	
	/**
	 * HTTP connection handler
	 * @author gonzalo.martin
	 *
	 */
	private class DoGetEntity {
		
		HttpURLConnection con;
		String response;
		InputStream inputStream;
		
		public DoGetEntity(String url) throws IOException{
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			inputStream = con.getInputStream();
			response = con.getResponseMessage();
		}
		
		public String getResponse(){
			return this.response;
		}
		
		public String parseInputStream(){
			return Utils.parseLine(inputStream);
		}
		
		public void disconnect(){
			this.con.disconnect();
		}
		
	}
}
