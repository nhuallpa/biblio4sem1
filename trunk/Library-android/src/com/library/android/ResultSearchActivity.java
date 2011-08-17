package com.library.android;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.library.android.domain.Book;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookListView;
import com.library.android.view.LibraryHeaderView;

public class ResultSearchActivity extends Activity {
	
	private static String TAG = "ResultSearchActivity";
	
	private BookListView bookListView;
	private LibraryHeaderView header;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book_list_view);
		bookListView = (BookListView)findViewById(R.id.book_list);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app);
		String q_search = getIntent().getExtras().getString("q_search");
		
		List<Book> booksFounded = BookServicesImpl.getInstance(this).findBooks(q_search);
		bookListView.setBookList(booksFounded);
		header.setInfo(booksFounded.size() + " results found for search: " + q_search);
	}
	
//	public void onStart(){
//		super.onStart();
//
//		ResultAsyncTask d = new ResultAsyncTask();
//		
//		d.execute();
//
//	}
//	
//	private class ResultAsyncTask extends AsyncTask<Void, Context, List<Book>> {
//
//		
//		@Override
//		protected List<Book> doInBackground(Void... params) {
//			
//			Bundle extras = getIntent().getExtras();
//
//			String inputText = (String)extras.get("inputText");
//			
////			return BookServicesImpl.findBook(inputText);
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(List<Book> results){
//				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
//				 
//				
//				if(results.size() > 0){
//					for(Book aBook : results){
//						HashMap<String,String> temp = new HashMap<String,String>();
//						temp.put("title", aBook.getTitle());
//						temp.put("author", aBook.getAuthor());
////						temp.put("score", aBook.getScore().toString());
//						list.add(temp);
//					}
//				
//				
////				SimpleAdapter simpleAdapter = new SimpleAdapter(ResultSearchActivity.this, list, R.layout.custom_list_view, new String[] { "title", "author", "score"},
////				        new int[] { R.id.text1, R.id.text2, R.id.text3});
////		        		setListAdapter(simpleAdapter);
//				}
////				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListaAmigosActivity.this,
////				        R.layout.amigos, R.id.text_item, myStrings);
////
////				setListAdapter(arrayAdapter);
//
//		
//		}
//
//    }

}
