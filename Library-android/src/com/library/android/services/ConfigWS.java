package com.library.android.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConfigWS {
	
	public static String APP = "http://biblioteca-web.appspot.com";
	public static String WS_SEARCH = APP + "/search?";
	public static String WS_LOGIN = APP + "/login?";
	public static String WS_UPLOAD = APP + "/upload?";
	
	private String TOKEN;
	private String user;
	private static ConfigWS instance; 
	
	public static ConfigWS getInstance(){
		if(instance == null){
			instance = new ConfigWS();
		}
		
		return instance;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setToken(String token){
		this.TOKEN = token;
	}
	
	public String getToken(){
		return TOKEN;
	}
	
	public String md5(String s){
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();
	        
	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";

	}
	
	

}
