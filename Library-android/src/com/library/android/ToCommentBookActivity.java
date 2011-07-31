package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.library.android.view.ToCommentBookView;

public class ToCommentBookActivity extends Activity {
	
	private ToCommentBookView toCommentBookView;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.to_comment_book_content);
		toCommentBookView = (ToCommentBookView) findViewById(R.id.to_comment_book_content_relative);
		
		final String title = getIntent().getExtras().getString("bookTitle");
		toCommentBookView.setBookTitle(title);
		toCommentBookView.getCommentButton().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String text = toCommentBookView.getTextFromInput();
				if(text != null){
					Toast.makeText(ToCommentBookActivity.this, "Comment Book..", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(ToCommentBookActivity.this, "Invalid input...", Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}

}
