package com.library.android.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.library.android.domain.Book;

public class BookListView extends ListView {
	
	public BookListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}
	
	private void init(Context context) {		
		NewsAdapter newsAdapter = new NewsAdapter();
		setAdapter(newsAdapter);
		
	}
	
	/**
	 * Set the list of news
	 * @param bookList
	 */
	public void setBookList(List<Book> bookList){
		NewsAdapter newsAdapter = (NewsAdapter)getAdapter();
		newsAdapter.setBooks(bookList);
		
	}
	
	
	private class NewsAdapter extends BaseAdapter{
		private List<Book> bookList;

		@Override
		public int getCount() {
			if(bookList != null){
				return bookList.size();
			}
			return 0;
		}
		
		public void setBooks(List<Book> bookList){
			this.bookList = bookList;
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			if(bookList != null && position >= 0 && position < bookList.size()){
				return bookList.get(position);
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
			BookListItemView v = null;
			if(obj != null && obj instanceof Book){
				Book aBook = (Book)obj;
				if(convertView instanceof BookListItemView){
					v = (BookListItemView)convertView;
				}else{
					v =  new BookListItemView(getContext());
				}
				v.setItemBook(aBook);
			}
			return v;
		}
	}
}
