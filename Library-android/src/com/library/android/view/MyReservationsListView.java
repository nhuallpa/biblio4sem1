package com.library.android.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.library.android.dto.Reservation;

public class MyReservationsListView extends ListView {

	public MyReservationsListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}
	
	private void init(Context context) {		
		ReservationsAdapter reservationsAdapter = new ReservationsAdapter();
		setAdapter(reservationsAdapter);
		
	}
	
	/**
	 * Set the list of reservations
	 * @param reservationsList
	 */
	public void setReservationsList(List<Reservation> reservationsList){
		ReservationsAdapter reservationsAdapter = (ReservationsAdapter)getAdapter();
		reservationsAdapter.setReservations(reservationsList);
		
	}
	
	
	private class ReservationsAdapter extends BaseAdapter{
		private List<Reservation> reservationsList;

		@Override
		public int getCount() {
			if(reservationsList != null){
				return reservationsList.size();
			}
			return 0;
		}
		
		public void setReservations(List<Reservation> reservationsList){
			this.reservationsList = reservationsList;
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			if(reservationsList != null && position >= 0 && position < reservationsList.size()){
				return reservationsList.get(position);
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
			MyReservationListItemView v = null;
			if(obj != null && obj instanceof Reservation){
				Reservation aReservation = (Reservation)obj;
				
				if(convertView instanceof MyReservationListItemView){
					v = (MyReservationListItemView)convertView;
				}else{
					v =  new MyReservationListItemView(getContext());
				}
				v.setReservationItem(aReservation);
			}
			return v;
		}
	}
	

}
