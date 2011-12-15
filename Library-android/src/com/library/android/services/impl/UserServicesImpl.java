package com.library.android.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
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

import com.library.android.config.ConfigurationManager;
import com.library.android.dto.Award;
import com.library.android.dto.Book;
import com.library.android.dto.Comment;
import com.library.android.dto.Library;
import com.library.android.dto.Reservation;
import com.library.android.dto.User;
import com.library.android.services.ConfigWS;
import com.library.android.utils.Utils;

public class UserServicesImpl{
	
	private static UserServicesImpl instance;
	private static Context ctx;
	
	public static UserServicesImpl getInstance(Context ctx){
		if(instance == null){
			instance = new UserServicesImpl(ctx);
		}
		return instance;
	}
	
	private UserServicesImpl(Context ctx){
		this.ctx = ctx;
	}
	
	public String login(String mail, String pass) throws IOException{
		 
		String url = ConfigWS.LOGIN + "?name=" + mail + "&password=" + pass;
		String id = null;
		try{
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection ();
		con.setDoInput(true);
		con.connect();
		String request = con.getResponseMessage();
		if(request.equals("OK")){
			JSONObject json = new JSONObject(Utils.parseLine(con.getInputStream()));
			if(json.getString("state").equals("OK")){
				id = json.getString("userId");
			}
		}
		}catch(IOException e){} 
		catch (JSONException e) {
			e.printStackTrace();
		}
        return id;
	}
	
	public List<Comment> getMyComments(){
		List<Comment> comments = new ArrayList<Comment>();
		User user = ConfigurationManager.getInstance(ctx).getCurrentUser();
		String url = ConfigWS.MY_COMMENTS +"?id="+ user.getId() ;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
				for(int i = 0; i < array.length(); i++){
					JSONObject obj = array.getJSONObject(i);
					Comment comment = new Comment();
					comment.setId(obj.getString("id"));
			    	comment.setDescription(obj.getString("description"));
			    	comment.setScore(Float.valueOf(obj.getString("score")));
			    	comment.setDate(obj.getString("date"));
			    	Book aBook = new Book();
			    	aBook.setBookId(Long.valueOf(obj.getJSONObject("book").getString("id")));
			    	aBook.setTitle(obj.getJSONObject("book").getString("name"));
//			    	aBook.setDescription(obj.getJSONObject("book").getString("description"));

			    	comment.setSources(user , aBook);
			    	comments.add(comment);
				}
			}
		}catch(IOException e){} 
		catch (JSONException e) {
			e.printStackTrace();
		}
        
		
		return comments;
	}

	public List<Reservation> getMyReservations(){
		List<Reservation> reservations = new ArrayList<Reservation>();
		User user = ConfigurationManager.getInstance(ctx).getCurrentUser();
		String url = ConfigWS.MY_RESERVATIONS +"?userId="+ user.getId();
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
				for(int i = 0; i < array.length(); i++){
					JSONObject obj = array.getJSONObject(i);
					Reservation aReservation = new Reservation();
					aReservation.setBook(obj.getJSONObject("book").getString("name"));
					aReservation.setDateReservation(obj.getString("date"));
					aReservation.setLibrary(new Library(
											obj.getJSONObject("library").getString("id"),
											obj.getJSONObject("library").getString("libraryName")));
					aReservation.setBookId(obj.getJSONObject("book").getString("id"));
					reservations.add(aReservation);
				}
			}
		}catch(IOException e){} 
		catch (JSONException e) {
			e.printStackTrace();
		}
        
		
		
		return reservations;
		
	}

	public int getMyScore(String userId){
		int myScore = -1;
		String url = ConfigWS.MY_SCORE +"?userId="+ userId;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				JSONObject json = new JSONObject(Utils.parseLine(con.getInputStream()));
				myScore = Integer.valueOf(json.getString("score"));
			}
		}catch(IOException e){} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return myScore;
	}
	
	public List<Award> getMyAwards(String userId){
		List<Award> list = new ArrayList<Award>();
		String url = ConfigWS.MY_AWARDS +"?userId="+ userId;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				JSONArray array = new JSONArray(Utils.parseLine(con.getInputStream()));
				for(int i = 0; i < array.length(); i++){
					JSONObject obj = array.getJSONObject(i);
					Award aAward = new Award();
					aAward.setId(obj.getString("awardId"));
					aAward.setDetail(obj.getString("detail"));
					aAward.setCategory(obj.getString("category"));
					aAward.setScore(Integer.valueOf(obj.getString("score")));
					aAward.setBitmap(getAwardPicture(obj.getString("category")));
					list.add(aAward);
				}
			}
		}catch(IOException e){} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Bitmap getAwardPicture(String category){
		Bitmap bitmap = null;
		String url = ConfigWS.PICTURE_AWARD;
		try{
			JSONObject json = new JSONObject();
			HttpPost request = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			json.put("category", category);
	        	        
            StringEntity se = new StringEntity(json.toString());  
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);
           
            HttpResponse httpResponse = client.execute(request);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
            	InputStream inputStream = httpResponse.getEntity().getContent();
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream), 50, 70, true);
            } 
			
            
		}catch (Exception e){
			
		}
		
		return bitmap;
	}
	
}

