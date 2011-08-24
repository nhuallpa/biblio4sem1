package com.library.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.MyCommentsListView;

	public class MyCommentsActivity extends Activity {
//		
//		private SimpleAdapter simpleAdapter = null; 
//		private ArrayList<HashMap<String, String>> list;
//		private Handler mHandler = new Handler();
//		private ConfigurationManager config;
		private Context ctx = MyCommentsActivity.this;
		
		
//		private CommentBookListView commentView;
		private MyCommentsListView myComments;
		private LibraryHeaderView header;

		public void onCreate(Bundle b){
			super.onCreate(b);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.my_comments);
			header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
			myComments = (MyCommentsListView) findViewById(R.id.my_comments_list);
			myComments.setCommentList(UserServicesImpl.getInstance(ctx).getMyComments());
//			commentView = (CommentBookListView)findViewById(R.id.my_comments_list);
//			commentView.setCommentList(UserServicesImpl.getInstance(ctx).getMyComments());
			header.setInfo("My Comments");
		}
		
		
		

//	public void onStart(){
//		super.onStart();
//		config = ConfigurationManager.getInstance(this);
//		LibraryAsyncTask task = new LibraryAsyncTask();
//		
//		task.execute();
//	}
//	
//	private class LibraryAsyncTask extends AsyncTask<Void, Context, List<Comment>>{
//
//		@Override
//		protected List<Comment> doInBackground(Void... arg0) {
//			
//			List<Comment> comments = UserServicesImpl.getInstance(ctx).getMyComments();
//						
//			return comments;
//		}
//		
//		@Override
//		protected void onPostExecute(List<Comment> results){
//			
//			list = new ArrayList<HashMap<String,String>>();
//			
//			if(results.size() > 0) {
//
//				for (Comment dato : results) {
//					HashMap<String,String> temp = new HashMap<String,String>();				
//							temp.put("description", dato.getDescription());
////							temp.put("user", dato.getUser().getName());
//							temp.put("book", dato.getBookSource().getTitle());
//					
//					list.add(temp);
//				}
//				simpleAdapter = new SimpleAdapter(MyCommentsActivity.this, list, R.layout.my_comments_item, new String[] { "description", "book"},
//			        new int[] { R.id.item_comment_description, R.id.item_comment_book_name});
//			        setListAdapter(simpleAdapter);
//			        
//			}else {
//				
//				
//				String msg = "You no have comments!!";
//				int duracion = Toast.LENGTH_SHORT;
//				
//				Toast toast = Toast.makeText(getApplicationContext(), msg, duracion);
//				int offsetX = 0;
//				int offsetY = 0;
//				
//				toast.setGravity(Gravity.CENTER_HORIZONTAL, offsetX, offsetY);
//				
//				toast.show();
//				
////				Intent i = new Intent(MisPosteos.this, PostearActivity.class);
////				startActivity(i);
//					
//				
//			}
//
//
//		}
//
//			
//		}
//		
	}
	

