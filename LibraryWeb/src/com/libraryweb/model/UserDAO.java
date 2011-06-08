package com.libraryweb.model;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class UserDAO {
	
	public static User getUserInfo(String callerId){
		
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		// The Query interface assembles a query
		Query q = new Query(User.NAME);
		q.addFilter("mail_login", Query.FilterOperator.EQUAL, callerId);
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);
		User userInfo = null;		

		for (Entity result : pq.asIterable()) {
			userInfo = new User();
			userInfo.setKey(result.getKey());
			userInfo.setMailLogin((String)result.getProperty("mail_login"));
			userInfo.setPassLogin((String)result.getProperty("pass_login"));
			break;
		}
		
		return userInfo;
	}
}
