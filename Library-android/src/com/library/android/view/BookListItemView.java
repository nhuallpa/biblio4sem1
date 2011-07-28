package com.library.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.android.R;

public class BookListItemView extends RelativeLayout {
	
	private ImageView bookPicture;
	private ImageButton scoreButton;
	private ImageButton commentsButton;
	private ImageButton moreDetailButton;
	private TextView bookTitleTv;
	private TextView bookAuthorTv;
	
	public BookListItemView(Context context) {
		super(context);
		inflate(context, R.layout.item_book_list, this);
		
		bookPicture = (ImageButton) findViewById(R.id.item_book_picture);
		scoreButton = (ImageButton) findViewById(R.id.item_icon_score);
		commentsButton = (ImageButton) findViewById(R.id.item_icon_comment);
		moreDetailButton = (ImageButton) findViewById(R.id.item_button_details);
	}

	public ImageView getBookPicture() {
		return bookPicture;
	}

	public void setBookPicture(Bitmap image) {
		if(image != null){			
			bookPicture.setImageBitmap(image);
		}else{
			bookPicture.setImageResource(R.drawable.not_picture_book);
		}
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

	public ImageButton getMoreDetailButton() {
		return moreDetailButton;
	}

	public void setMoreDetailButton(ImageButton moreDetailButton) {
		this.moreDetailButton = moreDetailButton;
	}

	
}
