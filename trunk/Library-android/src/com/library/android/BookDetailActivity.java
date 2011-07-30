package com.library.android;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.library.android.config.ConfigurationManager;
import com.library.android.domain.States;
import com.library.android.mock.LibraryMocks;
import com.library.android.view.BookDetailView;
import com.library.android.view.CommentBookListView;

public class BookDetailActivity extends Activity {
	
	private BookDetailView bookDetailView;
	private String bookState;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_content_detail);
		bookDetailView =(BookDetailView) findViewById(R.id.book_detail_content);
		
		setExtras(getIntent().getExtras());
		
		//mock
		fill();
	}
	
	private void setExtras(Bundle extras){
		bookDetailView.setBookTitle(extras.getString("titleBook"));
		bookDetailView.setBookAuthor(extras.getString("authorBook"));
		bookDetailView.setBookState(extras.getString("stateBook"));
		bookState = extras.getString("stateBook");
		bookDetailView.setBookISBN(extras.getString("isbnBook"));
		
		try {
			bookDetailView.setBookPicture(BitmapFactory.decodeStream(getAssets().open(extras.getString("picture"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void fill(){
		CommentBookListView bookList = bookDetailView.getCommentList();
		bookList.setCommentList(LibraryMocks.getInstance().getAllBooks().get(4).getListOfComments());
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		ConfigurationManager config = ConfigurationManager.getInstance(this);
        MenuInflater inflater = getMenuInflater();
        int menuId = 0;
        if(config.isLogged()){
        	if(this.bookState.equals(States.AVAILABLE.toString())){
            	menuId = R.menu.menu_book_detail;
            } else if (this.bookState.equals(States.RESERVED.toString()) || 
            			this.bookState.equals(States.DELIVERED.toString())) {
            	menuId = R.menu.menu_book_detail_not_available;
            }
        } else {
        	menuId = R.menu.menu_not_logged;
        }
        

        inflater.inflate(menuId, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()){

        	case R.id.menu_book_detail_comment: {
//        		Intent i = new Intent(BookDetailActivity.this, ToCommentBookActivity.class);
//        		startActivity(i);
        	}break;
        
            case R.id.menu_location: {
            	
            }break;
            
            case R.id.menu_book_detail_reserve: {
            	
            }break;
            
            case R.id.menu_book_detail_login: {
            	Intent i = new Intent(BookDetailActivity.this, LoginActivity.class);
            	startActivity(i);
            }break;

        }
        
        return true;
        }



}
