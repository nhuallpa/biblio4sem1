package com.libraryweb.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Config {
	
	public static String MAIL_PARAM = "mail_login";
	public static String PASS_PARAM = "pass_login";
	public static String ID_PARAM = "id_user";
	public static String BOOK_TITLE_PARAM = "title";
	public static String BOOK_ID_PARAM = "bookId";
	public static String TOKEN = "token";
	public static String URL = "http://biblioteca-web.appspot.com";
	public static String URL_LOGIN = URL + "/login";
	
	private static Config instance; 
	
	private Long ID;
	
	private Config(){
		ID = new Long(0);
	}
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		
		return instance;
	}
	
	public long getNextID(){
		ID++;
		return ID;
	}
	
	public long getActualID(){
		return ID;
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
