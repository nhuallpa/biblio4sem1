package com.library.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.R;

public class ToCommentBookView extends RelativeLayout {
	
	private TextView bookTitle;
	private EditText editText;
	private Button toCommentButton;
	
	public ToCommentBookView(Context context) {
		super(context);
		init(context);
		
	}
	
	/**
	 * Creates a SimpleTextField Widget with a defined Style.
	 * @param context
	 * @param attrs
	 */
	public ToCommentBookView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * Creates a SimpleTextField Widget with a defined Style and a defined style.
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ToCommentBookView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(final Context context) {
		inflate(context, R.layout.to_comment_book, this);
		this.bookTitle = (TextView) findViewById(R.id.to_comment_book_title_book);
		this.editText = (EditText) findViewById(R.id.to_comment_book_edit_text);
		this.toCommentButton = (Button) findViewById(R.id.to_comment_book_submmit_button);

	}
	
	public void setBookTitle(String text){
		this.bookTitle.setText(text);
	}
	
	public Button getCommentButton(){
		return this.toCommentButton;
	}
	
	public String getTextFromInput(){
		return this.editText.getText().toString();
	}
	
	

}
