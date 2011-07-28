package com.library.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.library.android.domain.Book;
import com.library.android.services.BookService;
import com.library.android.services.impl.BookServicesImpl;

public class MainActivity extends ListActivity {
	
	private BookService service;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_result_search);
        
    }
    
    public void onStart(){
		super.onStart();

		BookListAsyncTask asyncTask = new BookListAsyncTask();
		service = BookServicesImpl.getInstance();
		asyncTask.execute();

	}
	
	private class BookListAsyncTask extends AsyncTask<Void, Context, List<Book>> {

		
		@Override
		protected List<Book> doInBackground(Void... params) {
			
			return service.findAllBooks();
		}
		
		@Override
		protected void onPostExecute(List<Book> results){
				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
							
				if(results.size() > 0){
					for(Book aBook : results){
						HashMap<String,String> temp = new HashMap<String,String>();
						temp.put("title", aBook.getTitle());
						temp.put("author", aBook.getAuthor());
						temp.put("score", aBook.getScore().toString());
						temp.put("comments", String.valueOf(aBook.getListOfComments().size()));
						list.add(temp);
					}
				
				
				SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, list, R.layout.item_result_search, new String[] { "title", "author", "score", "comments"},
				        new int[] { R.id.item_title_book, R.id.item_author_book, R.id.item_score_book, R.id.item_comments_book});
		        		setListAdapter(simpleAdapter);
				}
//				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListaAmigosActivity.this,
//				        R.layout.amigos, R.id.text_item, myStrings);
//
//				setListAdapter(arrayAdapter);

		
		}

    }
}