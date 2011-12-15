package com.library.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.android.config.ConfigurationManager;
import com.library.android.dto.Award;
import com.library.android.services.impl.AwardServicesImpl;
import com.library.android.view.LibraryHeaderView;

public class AwardDetailActivity extends Activity {
	
	private TextView info;
	private ImageView picture;
	private LibraryHeaderView headerView;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.award_detail);
		info = (TextView) findViewById(R.id.award_detail_info);
		picture = (ImageView) findViewById(R.id.award_detail_picture);
		headerView = (LibraryHeaderView) findViewById(R.id.header_library_app2);
		String awardId = getIntent().getExtras().getString("awardId");
		dialog = new ProgressDialog(this);
		dialog.setMessage("Loading...");
		initTask(awardId);
//		showAwardDetail(awardId);
	}

	private void initTask(String awardId){
		ConfigurationManager config = ConfigurationManager.getInstance(getApplicationContext());
		if(!config.checkNetwork().equals("OK")){
			config.showErrorNetwork();
		} else {
			AwardListTask task = new AwardListTask();
			dialog.show();
			task.execute(awardId);
		}
		
	}
	
//	private void showAwardDetail(String awardId) {
//		Award award = AwardServicesImpl.getInstance().getAward(awardId);
//		if(award.getDetail() != null){
//			headerView.setInfo(award.getDetail());
//			info.setText(award.getInfo());
//			picture.setImageBitmap(award.getBitmap());
//			//TODO: implementar el layout
//		} else {
//			Toast.makeText(getApplicationContext(), "No se encontro el premio...", Toast.LENGTH_SHORT).show();
//		}
//		
//	}
	
	private class AwardListTask extends AsyncTask<String, Void, Award>{
		
		@Override
		protected Award doInBackground(String... params) {
			Award award = AwardServicesImpl.getInstance().getAward(params[0]);
			return award;
		}
		
		@Override
		protected void onPostExecute(Award award) {
			headerView.setInfo(award.getDetail());
			info.setText(award.getInfo());
			picture.setImageBitmap(award.getBitmap());
			dialog.dismiss();
		}

		
	}

}
