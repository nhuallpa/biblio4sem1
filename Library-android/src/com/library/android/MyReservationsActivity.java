package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import com.library.android.dto.Reservation;
import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.LibraryHeaderView;
import com.library.android.view.MyReservationsListView;

public class MyReservationsActivity extends Activity {
	
	private MyReservationsListView myReservations;
	private LibraryHeaderView header;
	private ProgressDialog dialog;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_reservations);
		myReservations = (MyReservationsListView) findViewById(R.id.my_reservations_list);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
//		myReservations.setReservationsList(UserServicesImpl.getInstance(this).getMyReservations());
		dialog = new ProgressDialog(this);
		dialog.setMessage("Please Wait!");
		dialog.show();
		init();
		header.setInfo("My Reservations");
		
	}
	
	private void init(){
		ReservationsTask task = new ReservationsTask();
		task.execute();
	}
	
	private class ReservationsTask extends AsyncTask<Void, Void, List<Reservation>>{

		@Override
		protected List<Reservation> doInBackground(Void... arg0) {
			List<Reservation> list = UserServicesImpl.getInstance(getApplicationContext()).getMyReservations();
			return list;
		}
		@Override
		protected void onPostExecute(List<Reservation> result) {
			myReservations.setReservationsList(result);
			dialog.dismiss();
		}
	}

}
