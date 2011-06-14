package com.libraryweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
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
			String token = config.md5(pass);//obtengo el token
			if(user == null){				
				user = new User();
				
				user.setMailLogin(mail);
				user.setPassLogin(pass);
				user.setToken(token);
				
				PersistenceManager pm = PMF.get().getPersistenceManager();

				try {
					pm.makePersistent(user);
				} finally {
					pm.close();
				}
				
//				String token = pass.trim();
				
				JSONObject jsonToken = new JSONObject();
				try {
					jsonToken.put("token", token);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
				resp.setContentType("text/x-json");         
				resp.setContentLength(jsonToken.length());
		        resp.setHeader("Cache-Control", "no-cache");
		        
		        try {
		             resp.getWriter().write(jsonToken.toString());
		        } catch (IOException e) {
		            
		        }              

				
				
			} 
			
//			resp.getWriter().println("UserKey: " + user.getKey());
//			log.log(Level.SEVERE, "UserKey: " + user.getKey());
		}
		
	
		
		
	}

}
