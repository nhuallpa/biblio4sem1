package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.dto.Book;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookListView;
import com.library.android.view.LibraryHeaderView;

public class BookListActivity extends Activity {

	private BookListView bookListView;
	private LibraryHeaderView header;
	private ConfigurationManager config;
	private ProgressDialog dialog;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        ShowDialog.progressDialog(this, 5);
        setContentView(R.layout.book_list_view);
        bookListView = (BookListView)findViewById(R.id.book_list);
        header = (LibraryHeaderView) findViewById(R.id.header_library_app);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait!");
       
//        init();
        BookListTask task = new BookListTask();
        dialog.show();
        task.execute();
        config = ConfigurationManager.getInstance(this);
    }
    
	 public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        int menuId = 0;
	        if(config.isLogged()){
	        	menuId = R.menu.menu_book_list;
	        } else {
	        	menuId = R.menu.menu_not_logged;
	        }
	        inflater.inflate(menuId, menu);
	        return true;
	    }
	    
	    public boolean onOptionsItemSelected (MenuItem item){

	        switch (item.getItemId()){

	        	case R.id.menu_search: {
	        		Intent i = new Intent(BookListActivity.this, SearchActivity.class);
	        		startActivity(i);
	        	}break;
	        
	            case R.id.menu_login:{
	            	Intent i = new Intent(BookListActivity.this, LoginActivity.class);
	            	i.putExtra(Constants.GO_TO_ACTIVITY, Constants.BOOK_LIST);
	            	startActivity(i);
	            	
	            }break;
	            
	            case R.id.menu_profile: {
	            	Intent i = new Intent(BookListActivity.this, UserProfileActivity.class);
	            	startActivity(i);
	            }break;
	            
	            case R.id.menu_refresh: {
//	            	onRestart();
	            }
	        }
	        
	        return true;
	        }
	    
	    private class BookListTask extends AsyncTask<Void, Void, List<Book>>{

			@Override
			protected List<Book> doInBackground(Void... params) {
				
				List<Book> books = BookServicesImpl.getInstance(getApplicationContext()).getTopBooks();
				return books;
			}
			
			@Override
			protected void onPostExecute(List<Book> result) {
				bookListView.setBookList(result);
				dialog.dismiss();
				super.onPostExecute(result);
			}
	    }

//	    @Override
//	    public void onRestart(){
//	    	super.onRestart();
//	    	init();
//	    }
}