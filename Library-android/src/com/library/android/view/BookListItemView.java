package com.library.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.R;

public class BookListItemView extends RelativeLayout {
	
	private ImageView bookPicture;
	private ImageButton scoreButton;
	private ImageButton commentsButton;
	private Button moreDetailButton;
	private TextView bookTitleTv;
	private TextView bookAuthorTv;
	private TextView bookISBNTv;
	private TextView bookScoreCount;
	private TextView bookCommentsCount;
	
	public BookListItemView(final Context context) {
		super(context);
		inflate(context, R.layout.item_book_list, this);
		
		bookPicture = (ImageView) findViewById(R.id.item_book_picture);
		scoreButton = (ImageButton) findViewById(R.id.item_icon_score);
		commentsButton = (ImageButton) findViewById(R.id.item_icon_comment);
		moreDetailButton = (Button) findViewById(R.id.item_button_details);
		bookTitleTv = (TextView) findViewById(R.id.item_title_book);
		bookAuthorTv = (TextView) findViewById(R.id.item_author_book);
		bookISBNTv = (TextView) findViewById(R.id.item_isbn_book);
		bookScoreCount =(TextView) findViewById(R.id.item_score_book);
		bookCommentsCount = (TextView) findViewById(R.id.item_comments_book);
		
		moreDetailButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Show Detail", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		scoreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Show Score", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		commentsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Comments..", Toast.LENGTH_SHORT).show();
				
			}
		});
	}

	public ImageView getBookPicture() {
		return bookPicture;
	}
	
	public void setBookScoreCount(String text){
		this.bookScoreCount.setText(text);
	}
	
	public void setBookCommentsCount(String text){
		this.bookCommentsCount.setText(text);
	}
	
	public String getBookCommentsCount(){
		return this.bookCommentsCount.toString();
	}
	
	public String getBookScoreCount(){
		return this.bookScoreCount.toString();
	}

	public void setBookPicture(Bitmap image) {
		if(image != null){			
			bookPicture.setImageBitmap(image);
		}else{
			bookPicture.setImageResource(R.drawable.not_picture_book);
		}
	}
	
	public void setBookISBN(String text){
		this.bookISBNTv.setText(text);
	}
	
	public String getBookISBN(){
		return this.bookISBNTv.getText().toString();
	}

	public String getBookTitle() {
		return bookTitleTv.toString();
	}

	public void setBookTitle(String text) {
		this.bookTitleTv.setText(text);
	}

	public String getBookAuthor() {
		return bookAuthorTv.getText().toString();
	}

	public void setBookAuthor(String text) {
		this.bookAuthorTv.setText(text);
	}

	public ImageButton getScoreButton() {
		return scoreButton;
	}

	public void setScoreButton(ImageButton scoreButton) {
		this.scoreButton = scoreButton;
	}

	public ImageButton getCommentsButton() {
		return commentsButton;
	}

	public void setCommentsButton(ImageButton commentsButton) {
		this.commentsButton = commentsButton;
	}

	public Button getMoreDetailButton() {
		return moreDetailButton;
	}

	public void setMoreDetailButton(Button moreDetailButton) {
		this.moreDetailButton = moreDetailButton;
	}

	
}
