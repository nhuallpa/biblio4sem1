package com.library.android.view;

import java.io.IOException;
import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.BookDetailActivity;
import com.library.android.R;
import com.library.android.domain.Book;

public class BookListItemView extends RelativeLayout {
	
	private ImageView bookPicture;
	private ImageButton scoreButton;
	private ImageButton commentsButton;
	private Button moreDetailButton;
	private TextView bookTitleTv;
	private TextView bookAuthorTv;
	private TextView bookStateTv;
	private TextView bookScoreCount;
	private TextView bookCommentsCount;
	
	private Book book;
	
	public BookListItemView(final Context context) {
		super(context);
		inflate(context, R.layout.item_book_list, this);
		
		bookPicture = (ImageView) findViewById(R.id.item_book_picture);
		scoreButton = (ImageButton) findViewById(R.id.item_icon_score);
		commentsButton = (ImageButton) findViewById(R.id.item_icon_comment);
		moreDetailButton = (Button) findViewById(R.id.item_button_details);
		bookTitleTv = (TextView) findViewById(R.id.item_title_book);
		bookAuthorTv = (TextView) findViewById(R.id.item_author_book);
		bookStateTv = (TextView) findViewById(R.id.item_state_book);
		bookScoreCount =(TextView) findViewById(R.id.item_score_book);
		bookCommentsCount = (TextView) findViewById(R.id.item_comments_book);
		
		moreDetailButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, BookDetailActivity.class);
				i.putExtra("titleBook", book.getTitle());
				i.putExtra("authorBook", book.getAuthor());
				i.putExtra("stateBook", book.getState().toString());
				i.putExtra("picture", book.getPicture());
				context.startActivity(i);
				
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
	
	public void setBookState(String text){
		this.bookStateTv.setText(text);
	}
	
	public String getBookState(){
		return this.bookStateTv.getText().toString();
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
	

	public void setItemBook(Book aBook) {
		DecimalFormat decimalFormat = new DecimalFormat("#.#");
		book = aBook;
		setBookTitle(aBook.getTitle());
		setBookState(String.valueOf(aBook.getState()));
		setBookAuthor(aBook.getAuthor());
		setBookScoreCount(decimalFormat.format(aBook.getScore()));
		setBookCommentsCount(String.valueOf(aBook.getListOfComments().size()));
		try {
			setBookPicture(BitmapFactory.decodeStream(getContext().getAssets().open(aBook.getPicture())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
}
