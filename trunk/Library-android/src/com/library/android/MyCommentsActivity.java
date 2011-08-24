package com.library.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.MyCommentsListView;

	public class MyCommentsActivity extends Activity {

		private Context ctx = MyCommentsActivity.this;

		private MyCommentsListView myComments;
		private LibraryHeaderView header;

		public void onCreate(Bundle b){
			super.onCreate(b);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.my_comments);
			header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
			myComments = (MyCommentsListView) findViewById(R.id.my_comments_list);
			myComments.setCommentList(UserServicesImpl.getInstance(ctx).getMyComments());
			header.setInfo("My Comments");
		}
		
	
	}
	

