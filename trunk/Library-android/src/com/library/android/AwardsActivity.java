package com.library.android;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
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
	private List<Award> list;
	
	private static String TAG = "AwardsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.awards);
		header = (LibraryHeaderView) findViewById(R.id.header_library_app2);
		listView = (AwardsListView) findViewById(R.id.awards_list);
		header.setInfo("Awards");
		setAwardList();
		
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

	private void setAwardList() {
		final ProgressDialog dialog = new ProgressDialog(AwardsActivity.this);
		dialog.setMessage("Loading...");
		dialog.show();
		new Handler().post(new Runnable() {
			
			@Override
			public void run() {
				Log.i(TAG, "INIT AWARDS LIST");
				
				list = AwardServicesImpl.getInstance().getAwardsList();
				
				listView.setAwardList(list);
				dialog.cancel();
				Log.i(TAG, "AWARDS LIST SETTED");
			}
		});
		
	}

}
