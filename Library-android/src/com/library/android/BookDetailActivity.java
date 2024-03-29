package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.dto.Book;
import com.library.android.dto.Library;
import com.library.android.dto.States;
import com.library.android.mock.LibraryMocks;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.services.impl.LibraryServicesImpl;
import com.library.android.view.BookDetailView;
import com.library.android.view.CommentBookListView;

public class BookDetailActivity extends Activity {
	
	private BookDetailView bookDetailView;
	private String bookState;
	private String bookId;
	private String bookName;
	private ProgressDialog dialog;
	
	private Context ctx = BookDetailActivity.this;

	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_content_detail);

		bookDetailView =(BookDetailView) findViewById(R.id.book_detail_content);
		bookId = getIntent().getExtras().getString(Constants.BOOK_ID);
	
		dialog = new ProgressDialog(this);
		dialog.setMessage("Please Wait!");
		dialog.show();
//		fillData();
		String state = ConfigurationManager.getInstance(getApplicationContext()).getNetworkState();
		if(state.equals(Constants.ONLINE)){
			init();
		} else {
			initMock();
		}
		
	}
	
	private void initMock() {
		Book book = LibraryMocks.getInstance().getBookById(Long.valueOf(bookId));
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
		dialog.dismiss();
	}

	private void init(){
		ConfigurationManager config = ConfigurationManager.getInstance(getApplicationContext());
		if(!config.checkNetwork().equals("OK")){
			config.showErrorNetwork();
		} else {
			BookDetailTask task = new BookDetailTask();
			task.execute();
		}
		
	}

//	private void fillData(){
//		Book book = BookServicesImpl.getInstance(this).getBookById(bookId);
//		CommentBookListView comments = bookDetailView.getCommentList();
//		comments.setCommentList(book.getListOfComments());
//		bookDetailView.setBookTitle(book.getTitle());
//		bookDetailView.setBookAuthor(book.getAuthor());
//		bookDetailView.setBookState(book.getState().toString());
//		bookState = book.getState().toString();
//		bookDetailView.setBookISBN(String.valueOf(book.getISBN()));
//		bookDetailView.setBookPicture(BookServicesImpl.getInstance(ctx).getPicture(book.getTitle()));
//		bookDetailView.setBookDescription(book.getDescription());
//		bookName = book.getTitle();
//		
//	}
	
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
        	
        	case R.id.menu_librarys: {
        		Intent i = new Intent(BookDetailActivity.this, LibraryListActivity.class);
        		i.putExtra(Constants.BOOK_ID, bookId);
        		i.putExtra(Constants.BOOK_NAME, bookName);
        		startActivity(i);
        	}break;
            
            case R.id.menu_book_detail_reserve: {
            	 final List<Library>  librarysList = LibraryServicesImpl.getInstance().getLibrarys(bookId);
            	 final AlertDialog.Builder builder = new Builder(ctx);
            	 String[] names = new String[librarysList.size()];
            	 for(int i = 0; i < librarysList.size(); i++){
            		 names[i] = librarysList.get(i).getName();
            	 }
            	 builder.setTitle("Select library - " + bookName);
            	 builder.setItems(names, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();						
						BookServicesImpl.getInstance(ctx).toReserveBook(bookId, librarysList.get(which).getLibraryId());
						Toast.makeText(ctx, "You has reserved " + bookName, Toast.LENGTH_SHORT).show();
					}
				});
            	 builder.show();
            	
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
            
            case R.id.menu_search: {
            	Intent i = new Intent(BookDetailActivity.this, SearchActivity.class);
            	startActivity(i);
            }break;

        }
        
        return true;
        }
    
    private class BookDetailTask extends AsyncTask<Void, Void, Book>{

		@Override
		protected Book doInBackground(Void... arg0) {
			Book book = BookServicesImpl.getInstance(getApplicationContext()).getBookById(bookId);
			return book;
		}
    	
		@Override
		protected void onPostExecute(Book book) {
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
			dialog.dismiss();
		}
    }
}
