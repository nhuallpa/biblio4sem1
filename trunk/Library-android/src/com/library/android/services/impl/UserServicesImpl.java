package com.library.android.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.library.android.config.ConfigurationManager;
import com.library.android.mock.LibraryMocks;
import com.library.android.repository.LibraryRepository;
import com.library.android.services.ConfigWS;
import com.library.android.utils.Utils;

public class UserServicesImpl{
	
	LibraryRepository repo;
	
	
	
	public static String login(String mail, String pass, Context ctx) throws IOException{
		 
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

}

