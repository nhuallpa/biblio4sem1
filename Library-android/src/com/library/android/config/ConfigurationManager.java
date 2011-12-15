package com.library.android.config;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.library.android.dto.User;

public class ConfigurationManager {
		
	private String userName = "";
	private String password = "";
	private String authKey = "";
	private User myUser;
	private String userId;
	
	private Context context;
	private SharedPreferences settings;
	private static ConfigurationManager configurationManager;	
	
	private ConfigurationManager(Context context) {
		this.context = context;
		this.myUser = new User();
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
	
	public boolean isLogged(){
		
		return !myUser.getName().equals("");
	}
	
	//borrar luego...
	public void setUser(User user){
		this.myUser = user;
	}
	
	private void load() {
		settings = context.getSharedPreferences(Constants.PREFS_NAME, 0);
		this.userName = settings.getString(Constants.USERNAME, "");
		this.password= settings.getString(Constants.PASSWORD, "");
		this.myUser.setName(userName);
	}
	
	public void save(){
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(Constants.USERNAME, this.userName);
		editor.putString(Constants.PASSWORD, this.password);
		myUser.setName(this.userName);
		myUser.setId(this.userId);
	}
	
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public User getCurrentUser(){
		return this.myUser;
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
	
	public void setAuthKey(String authKey){
		this.authKey = authKey;
	}
	
	public String getAuthKey(){
		return authKey;
	}
	
	public String checkNetwork(){
		String result = null;
	    try {
	        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo netInfo = cm.getActiveNetworkInfo();
	        if (netInfo != null && netInfo.isConnected()) {
	        	result = "OK";
	        } else {
	        	result = "No connection";
	        }
	    } catch (Exception e) {
	    	result = "Exception: " + e.getLocalizedMessage();
	    }
	    return result;
	}
	
	public void showErrorNetwork() {
		final AlertDialog.Builder builder = new Builder(context);
		 builder.setTitle("Warning");
		 builder.setMessage("Network  dissable");
		 builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

			 @Override
			 public void onClick(DialogInterface dialog, int which) {
				 dialog.dismiss();
			 }
		 });
		 builder.show();
	}

}
