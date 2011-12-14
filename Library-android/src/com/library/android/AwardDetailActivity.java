package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.dto.Award;
import com.library.android.services.impl.AwardServicesImpl;
import com.library.android.view.LibraryHeaderView;

public class AwardDetailActivity extends Activity {
	
	private TextView info;
	private ImageView picture;
	private LibraryHeaderView headerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.award_detail);
		info = (TextView) findViewById(R.id.award_detail_info);
		picture = (ImageView) findViewById(R.id.award_detail_picture);
		headerView = (LibraryHeaderView) findViewById(R.id.header_library_app2);
		String awardId = getIntent().getExtras().getString("awardId");
		showAwardDetail(awardId);
	}

	private void showAwardDetail(String awardId) {
		Award award = AwardServicesImpl.getInstance().getAward(awardId);
		if(award.getDetail() != null){
			headerView.setInfo(award.getDetail());
			info.setText(award.getInfo());
			picture.setImageBitmap(award.getBitmap());
			//TODO: implementar el layout
		} else {
			Toast.makeText(getApplicationContext(), "No se encontro el premio...", Toast.LENGTH_SHORT).show();
		}
		
	}

}
