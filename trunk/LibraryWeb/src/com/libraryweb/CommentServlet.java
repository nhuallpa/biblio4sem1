package com.libraryweb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libraryweb.model.Book;
import com.libraryweb.model.BookDAO;
import com.libraryweb.model.Config;
import com.libraryweb.model.PMF;

@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger("CommentServlet");
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		String bookId = req.getParameter(Config.BOOK_ID_PARAM);
		String comment = req.getParameter("comment");
		
		if(bookId != null && comment != null){
			
			Book aBook = BookDAO.getBook(bookId);
			
			if(aBook != null){
				
				log.log(Level.SEVERE, "El libro no es nulo");
				aBook.getComments().add(comment);
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					pm.makePersistent(aBook);
				} finally {
					pm.close();
				}
				
				resp.getWriter().println("Comentario subido: " + comment + 
										", al libro: " + aBook.getTitle());
				log.log(Level.SEVERE, "Comentario subido: " + comment + 
						", al libro: " + aBook.getTitle());
				
			} else {
				resp.getWriter().println("No se ha podido comentar");
				log.log(Level.SEVERE, "No se ha podido comentar");
			}
			
			
			
		}
		
		
	}
		

}
