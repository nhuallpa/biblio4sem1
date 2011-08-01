package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.ToCommentBookView;

public class ToCommentBookActivity extends Activity {
	
	private ToCommentBookView toCommentBookView;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.to_comment_book_content);
		toCommentBookView = (ToCommentBookView) findViewById(R.id.to_comment_book_content_relative);
		
		if(getIntent().getExtras() != null){
			String title = getIntent().getExtras().getString("bookTitle");
			toCommentBookView.setBookTitle(title);
		} else {
			toCommentBookView.setBookTitle("No title");
		}

		
		toCommentBookView.getCommentButton().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String text = toCommentBookView.getTextFromInput();
				if(!text.equals("")){
					Comment aComment = new Comment(text, new Book());
					BookServicesImpl.getInstance().toComment(new Long(123465), aComment);
					Toast.makeText(ToCommentBookActivity.this, "Send Comment..", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(ToCommentBookActivity.this, BookListActivity.class);
					startActivity(i);
				} else {
					Toast.makeText(ToCommentBookActivity.this, "Invalid input...", Toast.LENGTH_LONG).show();
				}	
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
		        Intent i = new Intent(ToCommentBookActivity.this, BookListActivity.class);
		        startActivity(i);
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}



}
