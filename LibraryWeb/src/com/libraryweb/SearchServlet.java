package com.libraryweb;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.ApplicationException;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.libraryweb.model.Book;
import com.libraryweb.model.BookDAO;
import com.libraryweb.model.Config;
import com.libraryweb.model.User;
import com.libraryweb.model.UserDAO;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger("SearchServlet");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		sendInfo(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	//	sendInfo(req,resp);
	}
	
	private void sendInfo(HttpServletRequest req, HttpServletResponse resp) {
		
		String mail = req.getParameter("mail");
		String bookId = req.getParameter(Config.BOOK_ID_PARAM);
		
		
		if(mail != null && bookId != null){
			User user = UserDAO.getUser(mail);
			Book book = BookDAO.getBook(bookId);
			
			if(user != null && book != null){
				
				try {
					
					
					JSONObject userJson = new JSONObject();
					userJson.put(Config.MAIL_PARAM, user.getMailLogin());
				//	userJson.put(Config.PASS_PARAM, user.getPassLogin());
					userJson.put("key", user.getKey());
					
					JSONObject bookJson = new JSONObject();
					bookJson.put(Config.BOOK_ID_PARAM, book.getBookId());
					bookJson.put(Config.BOOK_TITLE_PARAM, book.getTitle());
					
					JSONArray array = new JSONArray();
					array.put(userJson);
					array.put(bookJson);
					
					resp.setContentType("text/x-json;charset=UTF-8");           
			        resp.setHeader("Cache-Control", "no-cache");
			        try {
			             resp.getWriter().write(array.toString());
			        } catch (IOException e) {
			            
			        }                               
					
					
					//hay que seguir implementando...
//					resp.getWriter().println("Book Title: " + book.getTitle());
//					log.log(Level.SEVERE, "Book Title: " + book.getTitle());
//					
//					resp.getWriter().println("BookId: " + book.getBookId());
//					log.log(Level.SEVERE, "BookId: " + book.getBookId());
//					
//					resp.getWriter().println("Key user: " + user.getKey());
//					log.log(Level.SEVERE, "Key user: " + user.getKey());
					
					
				}  catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				try {
					resp.getWriter().println("No existe el usuario con mail: " + mail);
					log.log(Level.SEVERE, "No existe el usuario con mail: " + mail);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
	}

	
		

}
