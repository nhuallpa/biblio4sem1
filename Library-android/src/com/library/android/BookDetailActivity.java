package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.library.android.mock.LibraryMocks;
import com.library.android.view.BookDetailView;

public class BookDetailActivity extends Activity {
	
	private BookDetailView bookDetailView;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_content_detail);
		bookDetailView =(BookDetailView) findViewById(R.id.book_detail_content);

		Bundle extras = getIntent().getExtras();
		String title = extras.getString("titleBook");
		bookDetailView.setBookTitle(title);
		
		//mock
		bookDetailView.getCommentList().setCommentList(LibraryMocks.getAllBooks().get(0).getListOfComments());
		
	}



}
