package com.library.android.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.library.android.dto.Comment;

public class MyCommentsListView extends ListView {
	
	public MyCommentsListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}
	
	private void init(Context context) {		
		NewsAdapter newsAdapter = new NewsAdapter();
		setAdapter(newsAdapter);
		
	}
	
	/**
	 * Set the list of comments
	 * @param commentList
	 */
	public void setCommentList(List<Comment> commentList){
		NewsAdapter newsAdapter = (NewsAdapter)getAdapter();
		newsAdapter.setComments(commentList);
		
	}
	
	
	private class NewsAdapter extends BaseAdapter{
		private List<Comment> commentList;

		@Override
		public int getCount() {
			if(commentList != null){
				return commentList.size();
			}
			return 0;
		}
		
		public void setComments(List<Comment> bookList){
			this.commentList = bookList;
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			if(commentList != null && position >= 0 && position < commentList.size()){
				return commentList.get(position);
			}
			
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			Object obj = getItem(position);
			MyCommentListItemView v = null;
			if(obj != null && obj instanceof Comment){
				Comment aComment = (Comment)obj;
				
				if(convertView instanceof BookListItemView){
					v = (MyCommentListItemView)convertView;
				}else{
					v =  new MyCommentListItemView(getContext());
				}
				v.setCommentItem(aComment);
			}
			return v;
		}
	}
	

}
