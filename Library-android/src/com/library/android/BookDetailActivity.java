package com.library.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.domain.Book;
import com.library.android.domain.States;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookDetailView;
import com.library.android.view.CommentBookListView;

public class BookDetailActivity extends Activity {
	
	private BookDetailView bookDetailView;
	private String bookState;
//	private Bundle extras;
	private String bookId;
	private String bookName;
	
	private Context ctx = BookDetailActivity.this;
	
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_content_detail);
		bookDetailView =(BookDetailView) findViewById(R.id.book_detail_content);
		bookId = getIntent().getExtras().getString(Constants.BOOK_ID);
		fillData();
		
	}
	
	private void fillData(){
		Book book = BookServicesImpl.getInstance(this).getBookById(bookId);
		CommentBookListView comments = bookDetailView.getCommentList();
		comments.setCommentList(book.getListOfComments());
		bookDetailView.setBookTitle(book.getTitle());
		bookDetailView.setBookAuthor(book.getAuthor());
		bookDetailView.setBookState(book.getState().toString());
		bookState = book.getState().toString();
		bookDetailView.setBookISBN(String.valueOf(book.getISBN()));
		bookDetailView.setBookPicture(BookServicesImpl.getInstance(ctx).getPicture(book.getTitle()));
		bookDetailView.setBookDescription(book.getDescription());
		bookName = book.getTitle();
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
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
        		Intent i = new Intent(BookDetailActivity.this, ToCommentBookActivity.class);
        		i.putExtra(Constants.BOOK_ID, bookId);
        		i.putExtra(Constants.BOOK_NAME, bookName);
        		startActivity(i);
        	}break;
            
            case R.id.menu_book_detail_reserve: {
            	final Book book = BookServicesImpl.getInstance(this).getBookById(bookId);
            	
            	final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            	alertDialog.setTitle(getString(R.string.reserve_title) + " " +book.getTitle());
            	alertDialog.setMessage(getString(R.string.are_you_sure));
            	alertDialog.setButton(getString(R.string.reserve_button), new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialog, int which) {
            		  BookServicesImpl.getInstance(ctx).toReserveBook(String.valueOf(book.getBookId())); 
            		  Intent i = new Intent(BookDetailActivity.this, BookListActivity.class);
              		  startActivity(i);
            	   }
            	});
            	alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
             	   public void onClick(DialogInterface dialog, int which) {

             		   alertDialog.closeOptionsMenu();
             		  
             	   }
             	});
            	
            	alertDialog.setIcon(R.drawable.logo_library);
            	alertDialog.show();
            	
            	
            }break;
            
            case R.id.menu_login: {
            	Intent i = new Intent(BookDetailActivity.this, LoginActivity.class);
            	i.putExtra(Constants.GO_TO_ACTIVITY, Constants.BOOK_DETAIL);
				i.putExtra(Constants.BOOK_ID, bookId);
            	startActivity(i);
            }break;
            
            case R.id.menu_profile: {
            	Intent i = new Intent(BookDetailActivity.this, UserProfileActivity.class);
            	startActivity(i);
            }break;

        }
        
        return true;
        }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
		        Intent i = new Intent(BookDetailActivity.this, BookListActivity.class);
		        startActivity(i);
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}

}
