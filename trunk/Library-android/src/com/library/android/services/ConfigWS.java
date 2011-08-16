package com.library.android.services;


public class ConfigWS {
	
//	public static String APP = "http://biblioteca-web.appspot.com";
//	public static String URL_BASE = "http://192.168.1.100:8080/Library"; //DEVELOPMENT
	public static String URL_BASE = "http://library-grails.cloudfoundry.com"; //PRODUCTION
	
//	public static String FIND_ALL_BOOKS = URL_BASE + "/book/getAll";
	public static String BOOK_CONTROLLER = "/book";
	public static String COMMENT_CONTROLLER = "/comment";
	public static String FIND_TOP_BOOKS = URL_BASE + BOOK_CONTROLLER + "/getTopBooks";
	public static String FIND_BOOK = URL_BASE + BOOK_CONTROLLER + "/getBook?bookId=";
	public static String FIND_USER = URL_BASE + "/user/getUser?userId=";
	public static String FIND_RESERVATION = URL_BASE + "/reservation/getReservation?reservationId=";
	public static String FIND_COMMENT = URL_BASE + "/comment/getComment?commentId=";
	public static String FIND_BOOK_COMMENTS = URL_BASE + BOOK_CONTROLLER + "/getBookComments?bookId="; 
	public static String TO_COMMENT_BOOK = URL_BASE + COMMENT_CONTROLLER + "/toCommentBook";
	
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
	

	
	

}
