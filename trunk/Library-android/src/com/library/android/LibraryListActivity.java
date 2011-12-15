package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import com.library.android.config.Constants;
import com.library.android.dto.Library;
import com.library.android.services.impl.LibraryServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.LibrarysListView;

public class LibraryListActivity extends Activity{
	
	private LibrarysListView librarysListView;
	private LibraryHeaderView header;
	private String bookId;
	private ProgressDialog dialog;

	public void onCreate(Bundle b){
		super.onCreate(b);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.librarys_list_view);
        librarysListView = (LibrarysListView) findViewById(R.id.librarys_list);
        header = (LibraryHeaderView) findViewById(R.id.header_library_app3);
        bookId = getIntent().getExtras().getString(Constants.BOOK_ID);
        String bookName = getIntent().getExtras().getString(Constants.BOOK_NAME);
        header.setInfo(bookName + " availability");
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait!");
        dialog.show();
        init();
	}

	private void init() {
//		librarysListView.setLibrarysList(LibraryServicesImpl.getInstance().getLibrarys(bookId));
		LibraryListTask task = new LibraryListTask();
		task.execute();
		
	}
	
	private class LibraryListTask extends AsyncTask<Void, Void, List<Library>>{

		@Override
		protected List<Library> doInBackground(Void... arg0) {
			List<Library> list = LibraryServicesImpl.getInstance().getLibrarys(bookId);
			return list;
		}
		@Override
		protected void onPostExecute(List<Library> result) {
			librarysListView.setLibrarysList(result);
			dialog.dismiss();
		}
	}
}
