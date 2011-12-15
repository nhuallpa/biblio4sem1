package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.dto.Award;
import com.library.android.dto.User;
import com.library.android.services.impl.AwardServicesImpl;
import com.library.android.view.AwardsListView;
import com.library.android.view.LibraryHeaderView;

public class AwardsActivity extends Activity {
	
	private LibraryHeaderView header;
	private AwardsListView listView;
	private ProgressDialog dialog;
	
	private static String TAG = "AwardsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.awards);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
		listView = (AwardsListView) findViewById(R.id.awards_list);
		header.setInfo("Awards");
		dialog = new ProgressDialog(AwardsActivity.this);
		dialog.setMessage("Please Wait!");
		dialog.show();
		init();
//		setAwardList();
		
	}
	
	private void init(){
		AwardTask task = new AwardTask();
		task.execute();
	}
	
	public boolean exchangeScore(Award award){
		boolean result = false;
		User user = ConfigurationManager.getInstance(this).getCurrentUser();
		result = AwardServicesImpl.getInstance().exchangeScore(award.getId(), user.getId()); 
		if(result){
			Toast.makeText(this, "Premio adquirido!!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "No se ha podido adquirir el premio", Toast.LENGTH_SHORT).show();
		}
		return result;
	}
	
	private class AwardTask extends AsyncTask<Void, Void, List<Award>>{

		@Override
		protected List<Award> doInBackground(Void... arg0) {
			Log.i(TAG, "INIT AWARDS LIST");
			List<Award> list = AwardServicesImpl.getInstance().getAwardsList();
			return list;
		}
		
		@Override
		protected void onPostExecute(List<Award> result) {
			listView.setAwardList(result);
			Log.i(TAG, "AWARDS LIST SETTED");
			dialog.cancel();
		}
		
	}

}
