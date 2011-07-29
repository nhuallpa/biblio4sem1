package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.library.android.mock.LibraryMocks;
import com.library.android.view.BookListView;

public class BookListActivity extends Activity {

	private BookListView bookListView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.book_list_view);
        bookListView = (BookListView)findViewById(R.id.book_list);
        init();
    }
    
    private void init(){
    	bookListView.setBookList(LibraryMocks.getInstance().getAllBooks());
    }
    
	 public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        
	        inflater.inflate(R.menu.menu_book_list, menu);
	        return true;
	    }
	    
	    public boolean onOptionsItemSelected (MenuItem item){

	        switch (item.getItemId()){

	        	case R.id.menu_search: {
	        		Intent i = new Intent(BookListActivity.this, SearchActivity.class);
	        		startActivity(i);
	        	}break;
	        
	            case R.id.menu_location: {
	            	
	            }break;

	            case R.id.menu_login:{
	            	Intent i = new Intent(BookListActivity.this, LoginActivity.class);
	            	startActivity(i);
	            	
	            }break;
	        }
	        
	        return true;
	        }

}