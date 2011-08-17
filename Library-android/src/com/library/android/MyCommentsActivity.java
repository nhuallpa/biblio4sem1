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
import android.view.Window;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.domain.Comment;
import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.CommentBookListView;

	public class MyCommentsActivity extends ListActivity {
		
		private SimpleAdapter simpleAdapter = null; 
		private ArrayList<HashMap<String, String>> list;
		private Handler mHandler = new Handler();
		private ConfigurationManager config;
		private Context ctx = MyCommentsActivity.this;
		
		
		private CommentBookListView commentView;

		public void OnCreate(Bundle b){
			super.onCreate(b);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.my_comments);
			config = ConfigurationManager.getInstance(this);
		
//			commentView = (CommentBookListView)findViewById(R.id.my_comments_list);
//			commentView.setCommentList(UserServicesImpl.getInstance(ctx).getMyComments());
		}
		
		
		

	public void onStart(){
		super.onStart();
		config = ConfigurationManager.getInstance(this);
		LibraryAsyncTask task = new LibraryAsyncTask();
		
		task.execute();
	}
	
	private class LibraryAsyncTask extends AsyncTask<Void, Context, List<Comment>>{

		@Override
		protected List<Comment> doInBackground(Void... arg0) {
			
			List<Comment> comments = UserServicesImpl.getInstance(ctx).getMyComments();
						
			return comments;
		}
		
		@Override
		protected void onPostExecute(List<Comment> results){
			
			list = new ArrayList<HashMap<String,String>>();
			
			if(results.size() > 0) {

				for (Comment dato : results) {
					HashMap<String,String> temp = new HashMap<String,String>();				
							temp.put("description", dato.getDescription());
//							temp.put("user", dato.getUser().getName());
							temp.put("book", dato.getBookSource().getTitle());
					
					list.add(temp);
				}
				simpleAdapter = new SimpleAdapter(MyCommentsActivity.this, list, R.layout.my_comments_item, new String[] { "description", "book"},
			        new int[] { R.id.item_comment_description, R.id.item_comment_book_name});
			        setListAdapter(simpleAdapter);
			        
			}else {
				
				
				String msg = "You no have comments!!";
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

			
		}
		
	}
	

