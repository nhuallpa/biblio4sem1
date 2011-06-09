package com.libraryweb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.libraryweb.model.Book;
import com.libraryweb.model.BookDAO;
import com.libraryweb.model.Config;
import com.libraryweb.model.PMF;
import com.libraryweb.model.User;

@SuppressWarnings("serial")
public class UploadBookServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger("UploadBookServlet");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String bookId = req.getParameter(Config.BOOK_ID_PARAM);
		String title = req.getParameter(Config.BOOK_TITLE_PARAM);
		
		if(bookId != null && title != null){
			
			JSONObject data = new JSONObject();
			
			try {
				
				data.accumulate(Config.BOOK_ID_PARAM, bookId);
				data.accumulate(Config.BOOK_TITLE_PARAM, title);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Book book = BookDAO.getBookInfo(data);
			
			if(book == null){				
				book = new Book();
			}
			
		
			
		//	log.log(Level.SEVERE, "Volvio del DAO, Book: " + book.getTitle());
			
			if(book != null){
				
				log.log(Level.SEVERE, "El libro no es nulo");
				
				book.setBookId(Long.decode(bookId));
				book.setTitle(title);
				
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					pm.makePersistent(book);
				} finally {
					pm.close();
				}
				
				resp.getWriter().println("Libro subido: " + book.getTitle());
				log.log(Level.SEVERE, "Libro subido: " + book.getTitle());
				
				
				
			} else {
				
				resp.getWriter().println("No se subio el libro");
				log.log(Level.SEVERE, "No se subio el libro");
				
			}
		}
		
	}
}
