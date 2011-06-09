package com.library.android.services;

public class ConfigWS {
	
	public static String APP = "http://biblioteca-web.appspot.com";
	public static String WS_SEARCH = APP + "/search?";
	public static String WS_LOGIN = APP + "/login?";
	public static String WS_UPLOAD = APP + "/upload?";
	
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