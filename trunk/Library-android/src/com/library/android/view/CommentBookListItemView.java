package com.library.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.android.R;
import com.library.android.domain.Comment;
import com.library.android.domain.User;

public class CommentBookListItemView extends RelativeLayout {
	
	private TextView user;
	private TextView descr;
	
	private Comment comment;


	public CommentBookListItemView(Context context) {
		super(context);
		inflate(context, R.layout.comment_book_item, this);
		user = (TextView) findViewById(R.id.item_comment_user);
		descr = (TextView) findViewById(R.id.item_comment_descr);
	}
	
	public void setUser(User user){
		this.user.setText(user.getName());
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
	
	public void setCommentItem(Comment aComment) {
		comment = aComment;
		setDescription(aComment.getDescription());
		setUser(aComment.getUser());
		
	}


}