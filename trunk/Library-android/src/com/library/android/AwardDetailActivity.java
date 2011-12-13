package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.dto.Award;
import com.library.android.services.impl.AwardServicesImpl;

public class AwardDetailActivity extends Activity {
	
	private TextView title;
	private ImageView picture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.award_detail);
		title = (TextView) findViewById(R.id.award_detail_title);
		picture = (ImageView) findViewById(R.id.award_detail_picture);
		String awardId = getIntent().getExtras().getString("awardId");
		showAwardDetail(awardId);
	}

	private void showAwardDetail(String awardId) {
		Award award = AwardServicesImpl.getInstance().getAward(awardId);
		if(award.getDetail() != null){
			title.setText(award.getDetail());
			picture.setImageBitmap(award.getBitmap());
			//TODO: implementar el layout
		} else {
			Toast.makeText(getApplicationContext(), "No se encontro el premio...", Toast.LENGTH_SHORT).show();
		}
		
	}

}
