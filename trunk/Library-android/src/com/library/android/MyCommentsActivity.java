package com.library.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.mock.Mocks;

	public class MyCommentsActivity extends ListActivity {
		
		private SimpleAdapter simpleAdapter = null; 
		private ArrayList<HashMap<String, String>> list;
		private Handler mHandler = new Handler();
		private ConfigurationManager config;

		public void OnCreate(Bundle b){
			super.onCreate(b);
			setContentView(R.layout.my_comments);
			config = ConfigurationManager.getInstance(this);
		
		}
		
	
	

	public void onStart(){
		super.onStart();

		LibraryAsyncTask task = new LibraryAsyncTask();
		
		task.execute();
	}
	
	private class LibraryAsyncTask extends AsyncTask<Void, Context, List<Comment>>{

		@Override
		protected List<Comment> doInBackground(Void... arg0) {
			
//			List<Comment> comments = config.getCurrentUser().getListOfComments();
			List<Comment> comments = Mocks.getUser().getListOfComments();
						
			return comments;
		}
		
		@Override
		protected void onPostExecute(List<Comment> results){
			
			list = new ArrayList<HashMap<String,String>>();
			
			if(results.size() > 0) {
				

				for (Comment dato : results) {
					HashMap<String,String> temp = new HashMap<String,String>();
	
						//TODO: quisiera que salga el picture de cada usuario!!
//						try {
							
//							temp.put("picture", "http://graph.facebook.com/" + dato.getJSONObject("from").getString("id")+ "/picture");
//							temp.put("name", dato.getJSONObject("from").getString("name"));
//							temp.put("post", dato.getString("message"));
//							temp.put("fecha", parsearFecha(dato.getString("created_time")));
//							temp.put("hora", parsearHora(dato.getString("created_time")));
							temp.put("description", dato.getDescription());
							temp.put("user", dato.getUser().getName());
							temp.put("book", dato.getBookSource().getTitle());
//						} catch (JSONException e) {
//						
//							e.printStackTrace();
//						}
						

					list.add(temp);
				}



				simpleAdapter = new SimpleAdapter(MyCommentsActivity.this, list, R.layout.my_comments_item, new String[] { "description", "user", "book"},
			        new int[] { R.id.item_comment_description, R.id.item_comment_user_name, R.id.item_comment_book_name});
			        setListAdapter(simpleAdapter);
			        
			}else {
				
				
				String msg = "No hay ningun post!";
				int duracion = Toast.LENGTH_SHORT;
				
				Toast toast = Toast.makeText(getApplicationContext(), msg, duracion);
				int offsetX = 0;
				int offsetY = 0;
				
				toast.setGravity(Gravity.CENTER_HORIZONTAL, offsetX, offsetY);
				
				toast.show();
				
//				Intent i = new Intent(MisPosteos.this, PostearActivity.class);
//				startActivity(i);
					
				
			}


		}
		
//		private Book getBook(Comment aComment){
//			
//			//WS
//			Book book = new Book();
//			
//			return book;
//		}
			
		}
		
	}
	

