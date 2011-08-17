package com.library.android.services;


public class ConfigWS {
	
//	public static String APP = "http://biblioteca-web.appspot.com";
	public static String URL_BASE = "http://172.17.193.113:8080/Library"; //DEVELOPMENT
//	public static String URL_BASE = "http://library-grails.cloudfoundry.com"; //PRODUCTION
	
	//controllers
	public static String BOOK_CONTROLLER = URL_BASE + "/book";
	public static String COMMENT_CONTROLLER = URL_BASE + "/comment";
	public static String RESERVATION_CONTROLLER = URL_BASE + "/reservation";
	public static String USER_CONTROLLER = URL_BASE + "/user";
	
	//actions
	public static String FIND_TOP_BOOKS = BOOK_CONTROLLER + "/getTopBooks";
	public static String FIND_BOOK = BOOK_CONTROLLER + "/getBook?bookId=";
	public static String FIND_USER = USER_CONTROLLER + "/getUser?userId=";
	public static String FIND_RESERVATION = URL_BASE + "/reservation/getReservation?reservationId=";
	public static String FIND_COMMENT = RESERVATION_CONTROLLER + "/getComment?commentId=";
	public static String FIND_BOOK_COMMENTS = BOOK_CONTROLLER + "/getBookComments?bookId="; 
	public static String TO_COMMENT_BOOK = COMMENT_CONTROLLER + "/toCommentBook";
	public static String TO_RESERVE_BOOK = RESERVATION_CONTROLLER + "/reserveBook";
	public static String LOGIN = USER_CONTROLLER + "/loginUser";
	public static String SEARCH_BOOK = BOOK_CONTROLLER + "/searchBook";
	public static String MY_COMMENTS = USER_CONTROLLER + "/getMyComments";
	public static String PICTURE_BOOK = BOOK_CONTROLLER + "/getPicture?name=";
	
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

	

	
	

}
