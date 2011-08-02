package com.library.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
    	bookListView.setBookList(BookServicesImpl.getInstance(this).getTopBooks());
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
	            	Intent i = new Intent(BookListActivity.this, ShowMapActivity.class);
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
	        }
	        
	        return true;
	        }

	    public void terminate()
	    {
	       Log.i("myid","terminated!!");
	       super.onDestroy();
	       this.finish();
	    }
	    
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event)  {
		    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
		    	
		    	final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            	alertDialog.setTitle("Quit Application");
            	alertDialog.setMessage("Are you sure?");
            	alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialog, int which) {
            		   terminate();
            	   }
            	});
            	alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
             	   public void onClick(DialogInterface dialog, int which) {
             		   alertDialog.cancel();
             	   }
             	});
            	
            	alertDialog.setIcon(R.drawable.logo_library);
            	alertDialog.show();
		    	
		        return true;
		    }

		    return super.onKeyDown(keyCode, event);
		}
}