package com.library.android.services;


public class ConfigWS {
	
//	public static String URL_BASE = "http://172.17.193.133:8080/Library"; //DEVELOPMENT
	public static String URL_BASE = "http://library-grails.cloudfoundry.com"; //PRODUCTION
	
	//controllers
	public static String BOOK_CONTROLLER = URL_BASE + "/book";
	public static String COMMENT_CONTROLLER = URL_BASE + "/comment";
	public static String RESERVATION_CONTROLLER = URL_BASE + "/reservation";
	public static String USER_CONTROLLER = URL_BASE + "/user";
	public static String LIBRARY_CONTROLLER = URL_BASE + "/library";
	public static String AWARD_CONTROLLER = URL_BASE + "/award";
	
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
	public static String PICTURE_BOOK = BOOK_CONTROLLER + "/getPicture";
	public static String GET_LIBRARYS = BOOK_CONTROLLER + "/getLibrarys";
	public static String GET_LIBRARY = LIBRARY_CONTROLLER + "/getLibrary";
	public static String DELETE_COMMENT = COMMENT_CONTROLLER + "/delComment";
	public static String MY_RESERVATIONS = USER_CONTROLLER + "/getMyReservations";
	public static String CANCEL_RESERVE = RESERVATION_CONTROLLER + "/MOBcancelReserve";
	public static String GET_ALL_LIBRARYS = LIBRARY_CONTROLLER + "/getAllLibrarys";
	public static String GET_AWARDS = AWARD_CONTROLLER + "/awardsList";
	public static String PICTURE_AWARD = AWARD_CONTROLLER + "/picture";
	public static String EXCHANGE_SCORE = AWARD_CONTROLLER + "/toExchange";
	public static String MY_SCORE = USER_CONTROLLER + "/myScore";
	public static String GET_AWARD = AWARD_CONTROLLER + "/getAward";
	
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
