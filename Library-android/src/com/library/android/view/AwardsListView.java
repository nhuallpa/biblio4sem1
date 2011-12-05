package com.library.android.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.library.android.dto.Award;

public class AwardsListView extends ListView {

	public AwardsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public AwardsListView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {		
		AwardsAdapter newsAdapter = new AwardsAdapter();
		setAdapter(newsAdapter);
		
	}
	
	/**
	 * Set the list of news
	 * @param bookList
	 */
	public void setAwardList(List<Award> awardList){
		AwardsAdapter awardAdapter = (AwardsAdapter)getAdapter();
		awardAdapter.setAwards(awardList);
		
	}
	
	
	private class AwardsAdapter extends BaseAdapter{
		private List<Award> awardList;

		@Override
		public int getCount() {
			if(awardList != null){
				return awardList.size();
			}
			return 0;
		}
		
		public void setAwards(List<Award> awardList){
			this.awardList = awardList;
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			if(awardList != null && position >= 0 && position < awardList.size()){
				return awardList.get(position);
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
			AwardsListItemView v = null;
			if(obj != null && obj instanceof Award){
				Award aAward = (Award)obj;
				if(convertView instanceof AwardsListItemView){
					v = (AwardsListItemView)convertView;
				}else{
					v =  new AwardsListItemView(getContext());
				}
				v.setAwardItem(aAward);
			}
			return v;
		}
	}

}
