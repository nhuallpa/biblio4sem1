package com.library.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.library.android.domain.Book;
import com.library.android.services.impl.BookServicesImpl;

public class ResultSearchActivity extends Activity {
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.main);


	}
	
	public void onStart(){
		super.onStart();

		ResultAsyncTask d = new ResultAsyncTask();
		
		d.execute();

	}
	
	private class ResultAsyncTask extends AsyncTask<Void, Context, List<Book>> {

		
		@Override
		protected List<Book> doInBackground(Void... params) {
			
			Bundle extras = getIntent().getExtras();

			String inputText = (String)extras.get("inputText");
			
			return BookServicesImpl.findBook(inputText);
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
						list.add(temp);
					}
				
				
//				SimpleAdapter simpleAdapter = new SimpleAdapter(ResultSearchActivity.this, list, R.layout.custom_list_view, new String[] { "title", "author", "score"},
//				        new int[] { R.id.text1, R.id.text2, R.id.text3});
//		        		setListAdapter(simpleAdapter);
				}
//				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListaAmigosActivity.this,
//				        R.layout.amigos, R.id.text_item, myStrings);
//
//				setListAdapter(arrayAdapter);

		
		}

    }

}
