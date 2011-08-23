package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.library.android.config.Constants;
import com.library.android.services.impl.LibraryServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.LibrarysListView;

public class LibraryListActivity extends Activity{
	
	private LibrarysListView librarysListView;
	private LibraryHeaderView header;
	private String bookId;

	public void onCreate(Bundle b){
		super.onCreate(b);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.librarys_list_view);
        librarysListView = (LibrarysListView) findViewById(R.id.librarys_list);
        header = (LibraryHeaderView) findViewById(R.id.header_library_app3);
        bookId = getIntent().getExtras().getString(Constants.BOOK_ID);
        init();
	}

	private void init() {
		librarysListView.setLibrarysList(LibraryServicesImpl.getInstance().getLibrarys(bookId));
		
	}
	
}
