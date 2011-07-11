package com.library.android.mock;

import com.library.android.domain.User;

public class UserMock {
	
	public static User getUser(){
		User user = new User();
		user.setName("Pepe");
		
		return user;
	}
	
	

}
