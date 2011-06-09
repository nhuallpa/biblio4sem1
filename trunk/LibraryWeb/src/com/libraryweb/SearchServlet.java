package com.libraryweb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
					
					//hay que seguir implementando...
					resp.getWriter().println("Book Title: " + book.getTitle());
					log.log(Level.SEVERE, "Book Title: " + book.getTitle());
					
					resp.getWriter().println("BookId: " + book.getBookId());
					log.log(Level.SEVERE, "BookId: " + book.getBookId());
					
					resp.getWriter().println("Key user: " + user.getKey());
					log.log(Level.SEVERE, "Key user: " + user.getKey());
					
					
				} catch (IOException e) {
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
