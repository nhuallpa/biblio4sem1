package com.library.android.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.R;
import com.library.android.dto.Reservation;
import com.library.android.services.impl.BookServicesImpl;

public class MyReservationListItemView extends RelativeLayout {
	
	private RelativeLayout relativeClick;
	private TextView bookName;
	private TextView reservationDate;
	
	private String bookId;

	public MyReservationListItemView(final Context context) {
		super(context);
		inflate(context, R.layout.my_reservations_item, this);
		relativeClick = (RelativeLayout) findViewById(R.id.item_my_reservation_relative_layout);
		bookName = (TextView) findViewById(R.id.item_my_reservation_book_name);
		reservationDate = (TextView) findViewById(R.id.item_my_reservation_date);
		
		relativeClick.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				final AlertDialog.Builder builder = new Builder(context);
	        	 String[] names = {"Cancel reserve","Change reserve"};
	        	 builder.setTitle(bookName.getText().toString());
	        	 builder.setItems(names, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						switch (which) {
						case 0:{
							if(!BookServicesImpl.getInstance(context).cancelReserve(bookId)){
								Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(context, "Reseve was canceled successfull!!", Toast.LENGTH_SHORT).show();
							}
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

	public void setReservationItem(Reservation aReservation) {
		bookName.setText(aReservation.getBook());
		reservationDate.setText(aReservation.getDateReservation());
		bookId = aReservation.getBookId();
	}

	
}
