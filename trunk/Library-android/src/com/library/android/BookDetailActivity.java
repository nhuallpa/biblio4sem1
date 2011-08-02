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
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.domain.Book;
import com.library.android.domain.States;
import com.library.android.mock.LibraryMocks;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookDetailView;
import com.library.android.view.CommentBookListView;

public class BookDetailActivity extends Activity {
	
	private BookDetailView bookDetailView;
	private String bookState;
//	private Bundle extras;
	
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_content_detail);
		bookDetailView =(BookDetailView) findViewById(R.id.book_detail_content);

		fillData(getIntent().getExtras().getLong(Constants.ISBN_BOOK));
		
	}
	
	private void fillData(Long isbn){
		Book book = BookServicesImpl.getInstance(this).getBookByISBN(isbn);
		CommentBookListView comments = bookDetailView.getCommentList();
		comments.setCommentList(book.getListOfComments());
		bookDetailView.setBookTitle(book.getTitle());
		bookDetailView.setBookAuthor(book.getAuthor());
		bookDetailView.setBookState(book.getState().toString());
		bookState = book.getState().toString();
		bookDetailView.setBookISBN(String.valueOf(isbn));
		try {
			bookDetailView.setBookPicture(BitmapFactory.decodeStream(getAssets().open(book.getPicture())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private void setExtras(){
//		if(extras != null){
//			bookDetailView.setBookTitle(extras.getString("titleBook"));
//			bookDetailView.setBookAuthor(extras.getString("authorBook"));
//			bookDetailView.setBookState(extras.getString("stateBook"));
//			bookState = extras.getString("stateBook");
//			bookDetailView.setBookISBN(extras.getString("isbnBook"));
//			try {
//				bookDetailView.setBookPicture(BitmapFactory.decodeStream(getAssets().open(extras.getString("picture"))));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
//	private void fill(){
//		//estaria bueno que traiga el book por el ISBN..
//		CommentBookListView bookList = bookDetailView.getCommentList();
//		bookList.setCommentList(LibraryMocks.getInstance().getTopBooks().get(2).getListOfComments());
//	}
	
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
	
//	@Override
//	public void onResume(){
//		setExtras();
//	}
    
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()){

        	case R.id.menu_book_detail_comment: {
        		Intent i = new Intent(BookDetailActivity.this, ToCommentBookActivity.class);
        		startActivity(i);
        	}break;
        
            case R.id.menu_location: {
            	Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(BookDetailActivity.this, ShowMapActivity.class);
        		startActivity(i);
            }break;
            
            case R.id.menu_book_detail_reserve: {
            	Toast.makeText(this, "Reserve", Toast.LENGTH_SHORT).show();
            }break;
            
            case R.id.menu_login: {
            	Intent i = new Intent(BookDetailActivity.this, LoginActivity.class);
            	i.putExtra(Constants.GO_TO_ACTIVITY, Constants.BOOK_DETAIL);
//				i.putExtra("titleBook", extras.getString("titleBook"));
//				i.putExtra("authorBook",extras.getString("authorBook"));
//				i.putExtra("stateBook", extras.getString("stateBook"));
//				i.putExtra("isbnBook", extras.getString("isbnBook"));
//				i.putExtra("picture", extras.getString("picture"));
            	startActivity(i);
            }break;
            
            case R.id.menu_profile: {
            	Intent i = new Intent(BookDetailActivity.this, MyCommentsActivity.class);
            	startActivity(i);
            }break;

        }
        
        return true;
        }



}
