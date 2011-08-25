package com.library.android;

import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.MyReservationsListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MyReservationsActivity extends Activity {
	
	private MyReservationsListView myReservations;
	private LibraryHeaderView header;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_reservations);
		myReservations = (MyReservationsListView) findViewById(R.id.my_reservations_list);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
		myReservations.setReservationsList(UserServicesImpl.getInstance(this).getMyReservations());
		header.setInfo("My Reservations");
	}

}
