package com.library.android.view;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.android.R;

public class CommentBookListItemView extends RelativeLayout {
	
	private TextView user;
	private TextView descr;

	public CommentBookListItemView(Context context) {
		super(context);
		inflate(context, R.layout.comment_book_item, this);
		user = (TextView) findViewById(R.id.item_comment_user);
		descr = (TextView) findViewById(R.id.item_comment_descr);
	}
	
	public void setUserName(String text){
		this.user.setText(text);
	}
	
	public String getUserName(){
		return this.user.getText().toString();
	}
	
	public void setDescription(String text){
		this.descr.setText(text);
	}
	
	public String getDescription(){
		return this.descr.getText().toString();
	}

}
