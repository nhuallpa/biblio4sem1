package com.library.android;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.library.android.domain.Book;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookListView;
import com.library.android.view.LibraryHeaderView;

public class ResultSearchActivity extends Activity {

	
	private BookListView bookListView;
	private LibraryHeaderView header;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_list_view);
		bookListView = (BookListView)findViewById(R.id.book_list);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app);
		String q_search = getIntent().getExtras().getString("q_search");
		
		List<Book> booksFounded = BookServicesImpl.getInstance(this).findBooks(q_search);
		if(booksFounded.size() == 0){
			header.setInfo("No results for " + q_search);
		} else {
			bookListView.setBookList(booksFounded);
			header.setInfo(booksFounded.size() + " results found for search: " + q_search);
		}
		
	}
}
