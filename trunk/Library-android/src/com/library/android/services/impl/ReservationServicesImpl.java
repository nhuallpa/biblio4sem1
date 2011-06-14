package com.library.android.services.impl;

import com.library.android.domain.Book;
import com.library.android.repository.LibraryRepository;
import com.library.android.services.ConfigWS;
import com.library.android.services.LibraryService;

public class ReservationServicesImpl implements LibraryService{

	LibraryRepository repo;
	
	public static boolean makeReservation(Book aBook){
		
		ConfigWS config = ConfigWS.getInstance();
		String url = "";
		
		
		return false;
	}
	
}
