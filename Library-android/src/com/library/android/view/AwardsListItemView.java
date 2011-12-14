package com.library.android.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.AwardDetailActivity;
import com.library.android.AwardsActivity;
import com.library.android.BookListActivity;
import com.library.android.LoginActivity;
import com.library.android.R;
import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.dto.Award;
import com.library.android.dto.User;
import com.library.android.services.impl.UserServicesImpl;

public class AwardsListItemView extends LinearLayout {
	
	private TextView detail;
	private TextView score;
	private ImageView picture;
	private RelativeLayout layout;
	private Award awardItem;
	
	public AwardsListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public AwardsListItemView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(final Context context){
		inflate(context, R.layout.award_item, this);
		layout = (RelativeLayout) findViewById(R.id.award_item_relative);
		detail = (TextView) findViewById(R.id.award_item_detail);
		score = (TextView) findViewById(R.id.award_item_score);
		picture = (ImageView) findViewById(R.id.award_item_picture);
		
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final User user = ConfigurationManager.getInstance(context).getCurrentUser();
				final AwardsActivity activity = (AwardsActivity) context;
				if(!ConfigurationManager.getInstance(context).isLogged()){
					Toast.makeText(context, "Necesita loguearse!", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(context, LoginActivity.class);
					intent.putExtra(Constants.BOOK_ID, "");
					intent.putExtra(Constants.BOOK_NAME, "");
					intent.putExtra(Constants.GO_TO_ACTIVITY, Constants.AWARDS);
					activity.startActivity(intent);
					
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					String[] items = new String[]{"Adquirir", "Info"};
					builder.setItems(items, new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog, int item) {
					    	switch (item) {
								case 0:{
									int myScore = UserServicesImpl.getInstance(context).getMyScore(user.getId());
									if(myScore < awardItem.getScore()){
										Toast.makeText(context, "No hay puntaje suficiente!", Toast.LENGTH_SHORT).show();
									} else {
										if(activity.exchangeScore(awardItem)){
											Intent i = new Intent(context, BookListActivity.class);
											context.startActivity(i);
										}
									}
									dialog.cancel();
								}break;
								case 1:{
									Intent i = new Intent(context, AwardDetailActivity.class);
									i.putExtra("awardId", awardItem.getId());
									context.startActivity(i);
								}break;
					    	}
					    }
					});
					builder.show();
				}

			}
		});
	}
	
	public void setAwardItem(Award item){
		this.detail.setText(item.getDetail());
		this.score.setText(String.valueOf(item.getScore()));
		this.picture.setImageBitmap(item.getBitmap());
		awardItem = item;
	}

}
