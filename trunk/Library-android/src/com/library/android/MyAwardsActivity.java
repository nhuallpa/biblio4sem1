package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.dto.Award;
import com.library.android.services.impl.UserServicesImpl;
import com.library.android.view.AwardsListView;
import com.library.android.view.LibraryHeaderView;

public class MyAwardsActivity extends Activity {
	
	private AwardsListView listView;
	private LibraryHeaderView header;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.awards);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
		listView = (AwardsListView) findViewById(R.id.awards_list);
		header.setInfo("My Awards");
		dialog = new ProgressDialog(MyAwardsActivity.this);
		dialog.setMessage("Please Wait!");
		dialog.show();
		init();
	}

	private void init() {
		ConfigurationManager config = ConfigurationManager.getInstance(getApplicationContext());
		if(!config.checkNetwork().equals("OK")){
			config.showErrorNetwork();
		} else {
			MyAwardsTask task = new MyAwardsTask();
			task.execute();
		}
	}
	
	private class MyAwardsTask extends AsyncTask<Void, Void, List<Award>>{

		@Override
		protected List<Award> doInBackground(Void... arg0) {
			String userId = ConfigurationManager.getInstance(getApplicationContext()).getCurrentUser().getId();
			List<Award> list = UserServicesImpl.getInstance(getApplicationContext()).getMyAwards(userId);
			return list;
		}
		
		@Override
		protected void onPostExecute(List<Award> awardList) {
			
			if(awardList.size() > 0){
				listView.setAwardList(awardList);
			} else {
				Toast.makeText(getApplicationContext(), "No awards!", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(MyAwardsActivity.this, UserProfileActivity.class);
				startActivity(i);
				finish();
			}
			
			dialog.cancel();
		}
	}
	
}
