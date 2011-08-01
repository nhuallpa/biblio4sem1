package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookListView;

public class BookListActivity extends Activity {

	private BookListView bookListView;
	private ConfigurationManager config;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.book_list_view);
        bookListView = (BookListView)findViewById(R.id.book_list);
        init();
        
        config = ConfigurationManager.getInstance(this);
        
        
    }
    
    private void init(){
    	bookListView.setBookList(BookServicesImpl.getInstance().getTopBooks());
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
	        
	            case R.id.menu_location: {

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
	        }
	        
	        return true;
	        }

}