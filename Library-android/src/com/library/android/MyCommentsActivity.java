package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import com.library.android.config.ConfigurationManager;
import com.library.android.dto.Comment;
import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.MyCommentsListView;

	public class MyCommentsActivity extends Activity {

		private Context ctx = MyCommentsActivity.this;

		private MyCommentsListView myComments;
		private LibraryHeaderView header;
		private ProgressDialog dialog;

		public void onCreate(Bundle b){
			super.onCreate(b);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.my_comments);
			header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
			myComments = (MyCommentsListView) findViewById(R.id.my_comments_list);
//			myComments.setCommentList(UserServicesImpl.getInstance(ctx).getMyComments());
			dialog = new ProgressDialog(this);
			dialog.setMessage("Please Wait!");
			dialog.show();
			header.setInfo("My Comments");
			init();
		}
		
		private void init(){
			ConfigurationManager config = ConfigurationManager.getInstance(getApplicationContext());
			if(!config.checkNetwork().equals("OK")){
				config.showErrorNetwork();
			} else {
				CommentsTask task = new CommentsTask();
				task.execute();
			}
			
		}
		
		private class CommentsTask extends AsyncTask<Void, Void, List<Comment>>{

			@Override
			protected List<Comment> doInBackground(Void... arg0) {
				List<Comment> list = UserServicesImpl.getInstance(ctx).getMyComments();
				return list;
			}
			
			@Override
			protected void onPostExecute(List<Comment> result) {
				myComments.setCommentList(result);
				dialog.dismiss();
			}
		}
	}
	

