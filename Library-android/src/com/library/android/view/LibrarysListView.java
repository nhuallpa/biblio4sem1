package com.library.android.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.library.android.domain.Book;
import com.library.android.domain.Library;

public class LibrarysListView extends ListView {

	public LibrarysListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}
	
	private void init(Context context) {		
		LibrarysAdapter newsAdapter = new LibrarysAdapter();
		setAdapter(newsAdapter);		
	}
	
	/**
	 * Set the list of news
	 * @param librarysList
	 */
	public void setLibrarysList(List<Library> librarysList){
		LibrarysAdapter newsAdapter = (LibrarysAdapter)getAdapter();
		newsAdapter.setLibrarys(librarysList);
		
	}
	
	
	private class LibrarysAdapter extends BaseAdapter{
		private List<Library> librarysList;

		@Override
		public int getCount() {
			if(librarysList != null){
				return librarysList.size();
			}
			return 0;
		}
		
		public void setLibrarys(List<Library> librarysList){
			this.librarysList = librarysList;
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			if(librarysList != null && position >= 0 && position < librarysList.size()){
				return librarysList.get(position);
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
			LibraryListItemView v = null;
			if(obj != null && obj instanceof Library){
				Library aLibrary = (Library)obj;
				if(convertView instanceof LibraryListItemView){
					v = (LibraryListItemView)convertView;
				}else{
					v =  new LibraryListItemView(getContext());
				}
				v.setItemLibrary(aLibrary);
			}
			return v;
		}
	}
	
}
