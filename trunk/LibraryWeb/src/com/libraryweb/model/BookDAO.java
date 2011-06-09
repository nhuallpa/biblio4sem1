package com.libraryweb.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

public class BookDAO {
	private static final Logger log = Logger.getLogger("BookDAO");
	public static Book getBookInfo(JSONObject data){
		String bookId = null;
		String title = null;
		Book book = null;
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		
		
		try {
			
			log.log(Level.SEVERE, "Entro al try");
			
			bookId = data.getString(Config.BOOK_ID_PARAM);
			title = data.getString(Config.BOOK_TITLE_PARAM);
			
			
			
//			The Query interface assembles a query
			Query q = new Query(Book.NAME);
			q.addFilter(Config.BOOK_ID_PARAM, Query.FilterOperator.EQUAL, data.get(bookId));
			q.addFilter(Config.BOOK_TITLE_PARAM, Query.FilterOperator.EQUAL, data.get(title));
			
			
			PreparedQuery pq = datastore.prepare(q);
					

			for (Entity result : pq.asIterable()) {
				book = new Book();
				book.setKey(result.getKey());
				book.setBookId(Long.decode((String)result.getProperty(Config.BOOK_ID_PARAM)));
				book.setTitle((String)result.getProperty(Config.BOOK_TITLE_PARAM));
				

				break;
			}
			
			log.log(Level.SEVERE, "Params: bookId:" + book.getBookId() + " - title:" + book.getTitle());
			
		} catch (JSONException e) {
			log.log(Level.SEVERE, "Excepcion : " + e.getMessage());
			e.printStackTrace();
		}
		
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore

		
		return book;
	}
	
	public static Book getBook(String bookId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Query query = pm.newQuery(Book.class);
		Book book = null;
		
		query.setFilter("bookId == bookIdParam");
		
		query.declareParameters("Long mail_loginParam");

		 List<Book> results = (List<Book>) query.execute(bookId);
		 
		 if(!results.isEmpty()){
			 book = results.get(0);
		 }
		
		return book;
	}

}
