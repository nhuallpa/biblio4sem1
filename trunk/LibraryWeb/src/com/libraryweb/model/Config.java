package com.libraryweb.model;

public class Config {
	
	public static String MAIL_PARAM = "mail_login";
	public static String PASS_PARAM = "pass_login";
	public static String ID_PARAM = "id_user";
	public static String BOOK_TITLE_PARAM = "title";
	public static String BOOK_ID_PARAM = "bookId";
	
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

}
