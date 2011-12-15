package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import com.library.android.dto.Book;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookListView;
import com.library.android.view.LibraryHeaderView;

public class ResultSearchActivity extends Activity {

	
	private BookListView bookListView;
	private LibraryHeaderView header;
	private String q_search;
	private ProgressDialog dialog;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_list_view);
		bookListView = (BookListView)findViewById(R.id.book_list);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app);
		q_search = getIntent().getExtras().getString("q_search");
		dialog = new ProgressDialog(this);
		dialog.setMessage("Please Wait!");
		dialog.show();
		init();
		
		
//		List<Book> booksFounded = BookServicesImpl.getInstance(this).findBooks(q_search);
//		if(booksFounded.size() == 0){
//			header.setInfo("No results for " + q_search);
//		} else {
//			bookListView.setBookList(booksFounded);
//			header.setInfo(booksFounded.size() + " results for " + q_search);
//		}
		
	}

	private void init() {
		ResultTask task = new ResultTask();
		task.execute();
	}
	
	private class ResultTask extends AsyncTask<Void, Void, List<Book>>{

		@Override
		protected List<Book> doInBackground(Void...voids) {
			List<Book> booksFounded = BookServicesImpl.getInstance(getApplicationContext()).findBooks(q_search);
			return booksFounded;
		}
		
		@Override
		protected void onPostExecute(List<Book> booksFounded) {
			if(booksFounded.size() == 0){
				header.setInfo("No results for " + q_search);
			} else {
				bookListView.setBookList(booksFounded);
				header.setInfo(booksFounded.size() + " results for " + q_search);
			}
			dialog.dismiss();
		}
	}
}
