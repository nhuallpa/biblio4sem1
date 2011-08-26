package com.library.android.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.R;
import com.library.android.UserProfileActivity;
import com.library.android.dto.Comment;
import com.library.android.services.impl.BookServicesImpl;

public class MyCommentListItemView extends RelativeLayout {
	
	private TextView bookName;
	private TextView description;
	private TextView dateComment;
	private RelativeLayout relative;
	private Comment comment;

	public MyCommentListItemView(final Context context) {
		super(context);
		inflate(context, R.layout.my_comments_item, this);
		bookName = (TextView) findViewById(R.id.item_my_comment_book_name);
		description = (TextView) findViewById(R.id.item_my_comment_description);
		dateComment = (TextView) findViewById(R.id.item_my_comment_date);
		relative = (RelativeLayout) findViewById(R.id.item_my_comment_relative_layout);
		
		relative.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
           	 final AlertDialog.Builder builder = new Builder(context);
        	 String[] names = {"Delete","Other"};
        	 builder.setTitle(comment.getBookSource().getTitle());
        	 builder.setItems(names, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					switch (which) {
					case 0:{
						BookServicesImpl.getInstance(context).deleteMyComment(comment.getId(),String.valueOf(comment.getBookSource().getBookId()));
						Toast.makeText(context, "Comment Deleted successfull", Toast.LENGTH_SHORT).show();
						Intent i = new Intent(context, UserProfileActivity.class);
						context.startActivity(i);
					}break;

					case 1:{
						
					}break;
				}
			}});
        	 builder.show();
				
				return false;
			}
		});
	}

	public void setCommentItem(Comment aComment) {
		comment = aComment;
		this.bookName.setText(aComment.getBookSource().getTitle());
		this.description.setText(aComment.getDescription());
		this.dateComment.setText(aComment.getDate());
	}

}
