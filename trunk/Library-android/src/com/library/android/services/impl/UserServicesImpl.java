package com.library.android.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.library.android.repository.LibraryRepository;
import com.library.android.services.ConfigWS;
import com.library.android.services.LibraryService;

public class UserServicesImpl implements LibraryService {
	
	LibraryRepository repo;
	
	public static boolean login(String mail, String pass){
		 
		String url = ConfigWS.WS_LOGIN + "mail_login=" + mail + "&pass_login=" + pass;
		int request = 0;
		boolean auth = false;
		try {
			//URL u = new URL(url);
			URLConnection connection = new URL(url).openConnection();
			
			// Http Method becomes POST
			connection.setDoOutput(true);

			// Encode according to application/x-www-form-urlencoded specification
			String content = "UserKey: " + URLEncoder.encode("key");
//			    "id=" + URLEncoder.encode ("username") +
//			    "&num=" + URLEncoder.encode ("password") +
//			    "&remember=" + URLEncoder.encode ("on") +
//			    "&output=" + URLEncoder.encode ("xml");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 

			// Try this should be the length of you content.
			// it is not neccessary equal to 48. 
			// content.getBytes().length is not neccessarily equal to content.length() if the String contains non ASCII characters.
			connection.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length)); 

			/**
			 * FALLA!!
			 */
			connection.connect();
			
			auth = true;
			
		} catch (MalformedURLException e) {
			auth = false;
			e.printStackTrace();
		} catch (IOException e) {
			auth = false;
			e.printStackTrace();
		} 
		
		
		return auth;
	}
}
