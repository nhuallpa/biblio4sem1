package com.libraryweb;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.libraryweb.model.Config;
import com.libraryweb.model.PMF;
import com.libraryweb.model.User;
import com.libraryweb.model.UserDAO;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger("LoginServlet");
	Config config = Config.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String mail = req.getParameter(Config.MAIL_PARAM);
		String pass = req.getParameter(Config.PASS_PARAM);
		
		JSONObject datos = new JSONObject();
		try {
			datos.accumulate(Config.MAIL_PARAM, mail);
			datos.accumulate(Config.PASS_PARAM, pass);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		User user = null;
		if(mail != null && pass != null){			
			user = UserDAO.getUserInfo(datos);
			
			if(user == null){				
				user = new User();
				
				user.setMailLogin(mail);
				user.setPassLogin(pass);
				
				
				PersistenceManager pm = PMF.get().getPersistenceManager();

				try {
					pm.makePersistent(user);
				} finally {
					pm.close();
				}
						
//				resp.getWriter().println("UserKey: " + user.getKey());
//				log.log(Level.SEVERE, "UserKey: " + user.getKey());
				
				
			} 
			
			resp.getWriter().println("UserKey: " + user.getKey());
			log.log(Level.SEVERE, "UserKey: " + user.getKey());
		}
		
	
		
		
	}

}
