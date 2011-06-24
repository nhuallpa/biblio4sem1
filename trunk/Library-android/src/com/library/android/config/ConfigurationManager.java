package com.library.android.config;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigurationManager {
	
	private static final String AUTHKEY = "AUTHKEY";
	private static final String USERNAME = "USERNAME";
	private static final String PASSWORD = "PASSWORD";
	private static final String PREFS_NAME = "LIBRARYANDROID";
	
	private String userName = "";
	private String password = "";
	
	private Context context;
	private SharedPreferences settings;
	private static ConfigurationManager configurationManager;	
	
	private ConfigurationManager(Context context) {
		this.context = context;
		load();
	}
	
	public static ConfigurationManager getInstance(Context context) {
		if (configurationManager == null) {
			configurationManager = new ConfigurationManager(context);
		}
		return configurationManager;
	}
	
	public boolean isConfigured() {
		return (!userName.equals("") && !password.equals(""));
	}
	
	private void load() {
		settings = context.getSharedPreferences(PREFS_NAME, 0);
		this.userName = settings.getString(USERNAME, "");
		this.password= settings.getString(PASSWORD, "");
	}
	
	public void save(){
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(USERNAME, this.userName);
		editor.putString(PASSWORD, this.password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
