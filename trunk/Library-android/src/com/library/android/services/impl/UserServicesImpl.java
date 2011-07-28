package com.library.android.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.library.android.repository.LibraryRepository;
import com.library.android.services.ConfigWS;
import com.library.android.services.BookService;
import com.library.android.utils.Utils;

public class UserServicesImpl {//implements LibraryWebServices {
	
	LibraryRepository repo;
	
	public static String login(String mail, String pass) throws IOException{
		 
//		String url = ConfigWS.WS_LOGIN + "mail_login=" + mail + "&pass_login=" + pass;
//		String token = null;
//		HttpURLConnection conn = null;
//				
//		URL u = new URL(url);
//		   conn = (HttpURLConnection) u.openConnection();
//
//		   conn.setDoOutput(true);
//
//		   String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
//		   
//           conn.setDoOutput(true);
//           OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//           wr.write(data);
//           wr.flush();
//
//           
//           // Get the response
//           BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//           String line = null;
//           String response = "";
//           while ((line = rd.readLine()) != null) {
//              response += line;
//           }
//
//           wr.close();
//           rd.close();
//           
//           if(response != null){
//        	 //make JSONObject
//               try {
//				JSONObject json = new JSONObject(response);
//				token = json.getString("token");
//			} catch (JSONException e) {
//				
//				e.printStackTrace();
//			}
//           }
         	String token = Utils.md5("123456");
           return token;
	}

}

