package com.library.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.android.R;

public class BookDetailView extends RelativeLayout {
	
	private TextView bookTitle;
	private ImageView bookPicture;
	private TextView bookAuthor;
	private TextView bookState;
	
	private CommentBookListView listView;

	public BookDetailView(Context context) {
		super(context);
		init(context);
		
	}
	
	/**
	 * Creates a SimpleTextField Widget with a defined Style.
	 * @param context
	 * @param attrs
	 */
	public BookDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * Creates a SimpleTextField Widget with a defined Style and a defined style.
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public BookDetailView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		inflate(context, R.layout.book_detail, this);
		bookTitle = (TextView) findViewById(R.id.book_detail_title_book);
		bookPicture = (ImageView) findViewById(R.id.book_detail_picture);
		bookAuthor = (TextView) findViewById(R.id.book_detail_author_book);
		listView = (CommentBookListView)findViewById(R.id.book_detail_content_comment);
		bookState = (TextView) findViewById(R.id.book_detail_state_book);
	}
	
	public CommentBookListView getCommentList(){
		return this.listView;
	}
	
	public void setBookTitle(String text){
		if(text != null){
			this.bookTitle.setText(text);
		}

	}
	
	public String getBookTitle(){
		return this.bookTitle.toString();
	}
	
	public void setBookState(String text){
		this.bookState.setText(text);
	}
	
	public void setBookPicture(Bitmap image) {
		if(image != null){			
			bookPicture.setImageBitmap(image);
		}else{
			bookPicture.setImageResource(R.drawable.not_picture_book);
		}
	}
	
	public void setBookAuthor(String text){
		if(text != null){
			this.bookAuthor.setText(text);
		}
	}
}
