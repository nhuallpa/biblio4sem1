package com.libraryweb.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User {
	
public static final String NAME = "User";
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    
    @Persistent
    private String mail_login;
    
    @Persistent
    private String pass_login;
    
    
    public User() {	
    }
    
    public User(String mail, String pass){
    	this.mail_login = mail;
    	this.pass_login = pass;
    }


	public User(String mail) {
		this.mail_login = mail;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getMailLogin() {
		return mail_login;
	}

	public void setMailLogin(String mail) {
		this.mail_login = mail;
	}

	public String getPassLogin() {
		return pass_login;
	}

	public void setPassLogin(String pass) {
		this.pass_login = pass;
	}
	
	@Override
	public String toString() {
		return key + " " + mail_login + " " + pass_login;
	}

}
