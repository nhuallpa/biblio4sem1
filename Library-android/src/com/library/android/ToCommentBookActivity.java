package com.library.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.library.android.config.Constants;
import com.library.android.dialog.ShowDialog;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.ToCommentBookView;

public class ToCommentBookActivity extends Activity {
	
	private ToCommentBookView toCommentBookView;
	private Context ctx = ToCommentBookActivity.this;
	private Spinner mySpinner;
	private static int MAX_CHAR = 100;
	private String bookId;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.to_comment_book_content);
		toCommentBookView = (ToCommentBookView) findViewById(R.id.to_comment_book_content_relative);
		toCommentBookView.setCharLeft(MAX_CHAR);
		mySpinner = toCommentBookView.getSpinner();
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.scores_arrays, android.R.layout.simple_spinner_item); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mySpinner.setAdapter(adapter); 
		
		if(getIntent().getExtras() != null){
			bookId = getIntent().getExtras().getString(Constants.BOOK_ID);
			String bookName = getIntent().getExtras().getString(Constants.BOOK_NAME);
//			book = BookServicesImpl.getInstance(ctx).getBookById(bookId);
			toCommentBookView.setBookTitle(bookName);
		} else {
			toCommentBookView.setBookTitle("No title");
		}

		toCommentBookView.getEditTextComment().addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int lenght = toCommentBookView.getEditTextComment().getText().length();
				toCommentBookView.setCharLeft(MAX_CHAR - lenght);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		toCommentBookView.getCommentButton().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String text = toCommentBookView.getTextFromInput();
				if(!text.equals("")){
					String scoreSelected = (String)mySpinner.getSelectedItem();
					ShowDialog.progressDialog(ctx, 5);
					BookServicesImpl.getInstance(ctx).toComment(bookId, text, scoreSelected);
					Toast.makeText(ToCommentBookActivity.this, "Send Comment..", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(ToCommentBookActivity.this, MyCommentsActivity.class);
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
