package com.library.android.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.library.android.domain.Book;
import com.library.android.repository.LibraryRepository;
import com.library.android.services.ConfigWS;
import com.library.android.services.LibraryService;

public class BookServicesImpl implements LibraryService {
	
	LibraryRepository repo;
	
	private static String url;
	
	private static BookServicesImpl instance;
	
	private BookServicesImpl(){
		ConfigWS config = ConfigWS.getInstance();
		url = ConfigWS.WS_SEARCH + "mail=" + config.getUser();
	}
	
	public static BookServicesImpl getInstance(){
		if(instance == null){
			instance = new BookServicesImpl();
		}
		return instance;
	}
	
	//tendria que devolver una lista de libros
	public static Book findBook(String text){
		
		String urlFinal = url + "&bookId=" + text;
//		String url = "http://biblioteca-web.appspot.com/search?mail=lalosoft@gmail.com&bookId=1234";
//		String request = null;
		String request = null;
		
		JSONArray array = null;
		JSONObject bookJs = null;
		Book book = new Book();
		try {
			URL u = new URL(urlFinal);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			request = con.getResponseMessage();
			if(request.equals("OK")){
				
				InputStream inputStream = con.getInputStream();
					
				// Parse it line by line
			    BufferedReader bufferedReader = new BufferedReader(
			                new InputStreamReader(inputStream));
			    StringBuffer sb = new StringBuffer();

			    String str = "";
			    while ((str = bufferedReader.readLine()) != null) {
			     sb.append(str);
			    }
			    array = new JSONArray(sb.toString());
				bookJs = array.getJSONObject(1);
				book.setBookId(bookJs.getLong("bookId"));
				book.setTitle(bookJs.getString("title"));
					
				con.disconnect();
			}
			//array = new JSONArray(request);
			
	
			
			
			
			
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
		
		
		
		
		
		
		
		return book;
	}
	
	public void addBook(String aRequest){
		
	}
	
	public boolean validate(Long bookId){
		
		return false;
	}
	
	public void toComment(String request){
		
	}


}
