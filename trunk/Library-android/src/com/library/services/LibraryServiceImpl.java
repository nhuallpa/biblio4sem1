package com.library.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.library.android.domain.Book;

public class LibraryServiceImpl implements LibraryService {

	private static LibraryServiceImpl instance;
	
	public static LibraryServiceImpl getInstance(){
		if(instance == null){
			instance = new LibraryServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<Book> booksFounded(List<Book> list, String text) {
		List<Book> founded = new ArrayList<Book>();
		for(int i = 0; i < list.size(); i++){
			Book aBook = list.get(i);
			Collections.sort(list);
//			Collections.binarySearch(list, text);
			if(isCoincidence(text, aBook.getTitle()) || isCoincidence(text, aBook.getAuthor())){
				founded.add(aBook);
			}
		}
		return founded;
	}
	
	public boolean isCoincidence(String text, String book){
		
	
		
		
		return true;
	}

}
