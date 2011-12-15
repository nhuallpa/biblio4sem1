package com.library.android.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.library.android.R;
import com.library.android.dto.Award;
import com.library.android.services.ConfigWS;
import com.library.android.utils.Utils;

public class AwardServicesImpl {
	
	private static AwardServicesImpl instance;
	
	public static AwardServicesImpl getInstance(){
		if(instance == null){
			instance = new AwardServicesImpl();
		}
		return instance;
	}
	
	public List<Award> getAwardsList(){
		List<Award> awardList = new ArrayList<Award>();
		String url = ConfigWS.GET_AWARDS;
		try{
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				JSONObject json = new JSONObject(Utils.parseLine(con.getInputStream()));
				JSONArray array = json.getJSONArray("awards");
				for(int i = 0; i < array.length(); i++){
					JSONObject obj = array.getJSONObject(i);
					Award aAward = new Award();
					aAward.setId(obj.getString("awardId"));
					aAward.setDetail(obj.getString("detail"));
					aAward.setCategory(obj.getString("category"));
					aAward.setScore(Integer.valueOf(obj.getString("score")));
					aAward.setBitmap(getAwardPicture(obj.getString("category")));
					awardList.add(aAward);
				}
			}
		} catch (IOException e){
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return awardList;
	}
	
	public Award getAward(String awardId){
		Award award = new Award();
		String url = ConfigWS.GET_AWARD + "?awardId=" + awardId;
		try {
			URL u = new URL(url);

			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
			con.setDoInput(true);
			con.connect();
			String request = con.getResponseMessage();
			if(request.equals("OK")){
				JSONObject json = new JSONObject(Utils.parseLine(con.getInputStream()));
				award.setCategory(json.getString("category"));
				award.setDetail(json.getString("detail"));
				award.setInfo(json.getString("info"));
				award.setScore(Integer.valueOf(json.getString("score")));
				Bitmap bitmap = getAwardPicture(json.getString("category"));
				if( bitmap != null){
					award.setBitmap(bitmap);
				}
				
			}
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
		return award;
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
	
	public boolean exchangeScore(String awardId, String userId){
		String url = ConfigWS.EXCHANGE_SCORE;
		boolean result = false;
		try{
			JSONObject json = new JSONObject();
			HttpPost request = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			json.put("awardId", awardId);
			json.put("userId", userId);
	        	        
            StringEntity se = new StringEntity(json.toString());  
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);
           
            HttpResponse httpResponse = client.execute(request);
            result = true;
//            result = httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK; 

		}catch (Exception e){
			
		}
		return result;
	}

}
