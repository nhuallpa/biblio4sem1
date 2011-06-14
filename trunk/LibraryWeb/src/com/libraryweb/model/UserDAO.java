package com.libraryweb.model;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

public class UserDAO {
	
	public static User getUserInfo(JSONObject data){
		
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		// The Query interface assembles a query
		Query q = new Query(User.NAME);
		Config config = Config.getInstance();
		try {
			q.addFilter(Config.MAIL_PARAM, Query.FilterOperator.EQUAL, data.get(Config.MAIL_PARAM));
			q.addFilter(Config.PASS_PARAM, Query.FilterOperator.EQUAL, data.get(Config.PASS_PARAM));
			q.addFilter(Config.TOKEN, Query.FilterOperator.EQUAL, data.get(Config.TOKEN));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);
		User userInfo = null;		

		for (Entity result : pq.asIterable()) {
			userInfo = new User();
			userInfo.setKey(result.getKey());
			userInfo.setMailLogin((String)result.getProperty(Config.MAIL_PARAM));
			userInfo.setPassLogin((String)result.getProperty(Config.PASS_PARAM));
			userInfo.setToken((String)result.getProperty(Config.TOKEN));
			break;
		}
		
		return userInfo;
	}
	
	public static User getUser(String mail){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Query query = pm.newQuery(User.class);
		User user = null;

		query.setFilter("mail_login == mail_loginParam");
		
		query.declareParameters("String mail_loginParam");

		 List<User> results = (List<User>) query.execute(mail);
		 
		 if(!results.isEmpty()){
			 user = results.get(0);
		 }
		 return user;


	}
	

}
