package com.library.android.services.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.library.android.dto.Library;
import com.library.android.dto.Location;
import com.library.android.services.ConfigWS;
import com.library.android.utils.Utils;

public class LibraryServicesImpl {
	
	private static LibraryServicesImpl instance;

	public static LibraryServicesImpl getInstance(){
		if(instance == null){
			instance = new LibraryServicesImpl();
		}
		return instance;
	}
	
	public Library getLibrary(String libId){
		Library library = null;
		String url = ConfigWS.GET_LIBRARY + "?libId=" + libId;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			String response = con.getResponseMessage();
			if(response.equals("OK")){

				JSONObject libraryJson = new JSONObject(Utils.parseLine(con.getInputStream()));
				library = new Library(libId, libraryJson.getString("name"));
				library.setEmail(libraryJson.getString("email"));
				library.setHomepage(libraryJson.getString("homepage"));
				library.setPhone(libraryJson.getString("phone"));
				Location aLocation = new Location(libraryJson.getJSONObject("location").getString("street"));
				aLocation.setCity(libraryJson.getJSONObject("location").getString("city"));
				aLocation.setCountry(libraryJson.getJSONObject("location").getString("country"));
				library.setLocation(aLocation);
			    
					
				
			}
				con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return library;
	}
	
	
	public List<Library> getLibrarys(String bookId){
		List<Library> librarys = new ArrayList<Library>();
		String url = ConfigWS.GET_LIBRARYS + "?bookId=" + bookId;
		try{
				URL u = new URL(url);
				HttpURLConnection con = (HttpURLConnection) u.openConnection ();
				
				con.setDoInput(true);
				con.setRequestMethod("GET");
				con.connect();
				String response = con.getResponseMessage();
				if(response.equals("OK")){

					JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
				    
				    for(int i = 0; i < array.length(); i++){
				    	JSONObject aLibrary = array.getJSONObject(i);
				    	Library library = new Library(aLibrary.getString("id"), aLibrary.getString("name"));
				    	library.setEmail(aLibrary.getString("email"));
				    	library.setPhone(aLibrary.getString("phone"));
				    	librarys.add(library);
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
	
		return librarys;
	}
	
	public List<Library> getLibrarys(){
		List<Library> librarys = new ArrayList<Library>();
		String url = ConfigWS.GET_ALL_LIBRARYS;
		try{
				URL u = new URL(url);
				HttpURLConnection con = (HttpURLConnection) u.openConnection ();
				
				con.setDoInput(true);
				con.setRequestMethod("GET");
				con.connect();
				String response = con.getResponseMessage();
				if(response.equals("OK")){

					JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
				    
				    for(int i = 0; i < array.length(); i++){
				    	JSONObject aLibrary = array.getJSONObject(i);
				    	Library library = new Library(aLibrary.getString("id"), aLibrary.getString("name"));
				    	Location aLocation = new Location(aLibrary.getJSONObject("location").getString("street"));
						aLocation.setCity(aLibrary.getJSONObject("location").getString("city"));
						aLocation.setCountry(aLibrary.getJSONObject("location").getString("country"));
						library.setLocation(aLocation);
						
						
				    	librarys.add(library);
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
	
		return librarys;
	}
}
