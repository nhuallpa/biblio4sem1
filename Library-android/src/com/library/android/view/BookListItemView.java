package com.library.android.view;

import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.BookDetailActivity;
import com.library.android.LoginActivity;
import com.library.android.R;
import com.library.android.ToCommentBookActivity;
import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.domain.Book;
import com.library.android.domain.States;
import com.library.android.services.impl.BookServicesImpl;

public class BookListItemView extends RelativeLayout {
	

	private ImageButton scoreButton;
	private ImageButton commentsButton;
	private ImageButton moreDetailButton;
	private TextView bookTitleTv;
	private TextView bookAuthorTv;
	private TextView bookStateTv;
	private TextView bookScoreCount;
	private TextView bookCommentsCount;
	private RelativeLayout relativeDetails;
	
	private Book book;
	private ConfigurationManager config;
	private Context ctx;
	
	public BookListItemView(final Context context) {
		super(context);
		ctx = context;
		inflate(context, R.layout.item_book_list, this);
		

		scoreButton = (ImageButton) findViewById(R.id.item_icon_score);
		commentsButton = (ImageButton) findViewById(R.id.item_icon_comment);
		moreDetailButton = (ImageButton) findViewById(R.id.item_button_more_detail);
		bookTitleTv = (TextView) findViewById(R.id.item_title_book);
		bookAuthorTv = (TextView) findViewById(R.id.item_author_book);
		bookStateTv = (TextView) findViewById(R.id.item_state_book);
		bookScoreCount =(TextView) findViewById(R.id.item_score_book);
		bookCommentsCount = (TextView) findViewById(R.id.item_comments_book);
		relativeDetails = (RelativeLayout) findViewById(R.id.relative_title_author_state);
		config = ConfigurationManager.getInstance(context);
		
		
//		bookTitleTv.setOnClickListener(myListener);
//		bookStateTv.setOnClickListener(myListener);
		
		moreDetailButton.setOnClickListener(myListener);
//		
		relativeDetails.setOnClickListener(myListener);
		
//		bookStateTv.setBackgroundColor(R.color.button_state);
//		bookTitleTv.setBackgroundColor(R.color.button_state);
		
		
		scoreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context,book.getTitle() + " - Score: " + book.getScore(), Toast.LENGTH_LONG).show();
				
			}
		});
		
		commentsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(config.isLogged()){
					Toast.makeText(context, "To Comment..", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(context, ToCommentBookActivity.class);
//					i.putExtra("bookId", book.getBookId());
					i.putExtra(Constants.BOOK_ID, String.valueOf(book.getBookId()));
					i.putExtra(Constants.BOOK_NAME, book.getTitle());
					
					context.startActivity(i);
				} else {
					Toast.makeText(context, "Comment: to Login", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(context, LoginActivity.class);
					i.putExtra(Constants.GO_TO_ACTIVITY, Constants.TO_COMMENT);
					i.putExtra(Constants.BOOK_ID, String.valueOf(book.getBookId()));
					i.putExtra(Constants.BOOK_NAME, book.getTitle());
					context.startActivity(i);
				}
			}
		});
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
			moreDetailButton.setImageBitmap(image);
		}else{
			moreDetailButton.setImageResource(R.drawable.not_picture_book);
		}
	}
	
	public void setBookState(String text){
		this.bookStateTv.setText(text);
		this.bookStateTv.setTextColor(Color.BLUE);
		if(text.equals(States.RESERVED.toString())){
			this.bookStateTv.setTextColor(Color.GRAY);
		} else if(text.equals(States.DELIVERED)){
			this.bookStateTv.setTextColor(Color.GREEN);
		}
		
		
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

	public ImageButton getMoreDetailButton() {
		return moreDetailButton;
	}

	public void setMoreDetailButton(ImageButton moreDetailButton) {
		this.moreDetailButton = moreDetailButton;
	}
	

	public void setItemBook(Book aBook) {
		DecimalFormat decimalFormat = new DecimalFormat("#");
		book = aBook;
		int commentsSize = aBook.getListOfComments().size();
		setBookTitle(aBook.getTitle());
		setBookState(String.valueOf(aBook.getState()));
		setBookAuthor(aBook.getAuthor());
	
		setBookCommentsCount(String.valueOf(commentsSize));
		if(commentsSize == 0){
			setBookScoreCount("0");
		} else {
			setBookScoreCount(decimalFormat.format(aBook.getScore()));
		}
		
		setBookPicture(BookServicesImpl.getInstance(ctx).getPicture(aBook.getTitle()));

		
		
		
	}

	
	private View.OnClickListener myListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i = new Intent(getContext(), BookDetailActivity.class);
			i.putExtra("titleBook", book.getTitle());
			i.putExtra("authorBook", book.getAuthor());
			i.putExtra("stateBook", book.getState().toString());
			i.putExtra("isbnBook", String.valueOf(book.getISBN()));
			i.putExtra(Constants.BOOK_ID, String.valueOf(book.getBookId()));
			getContext().startActivity(i);
			
		}
	};
	
}
