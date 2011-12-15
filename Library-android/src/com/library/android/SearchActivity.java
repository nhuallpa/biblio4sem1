package com.library.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.library.android.dialog.ShowDialog;

public class SearchActivity extends Activity {
	
	private EditText editText;
	private ImageButton searchButton;
	private ImageButton topTenButton;
	private ImageButton luckyButton;

	private Context CTX = SearchActivity.this; 
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_book);
		
		editText = (EditText)findViewById(R.id.edit_buscar);
		searchButton = (ImageButton) findViewById(R.id.search_button);
		topTenButton = (ImageButton) findViewById(R.id.fav_button);
		luckyButton = (ImageButton) findViewById(R.id.lucky_button);
		
		topTenButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
		
		
		luckyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				if(isValid(text)){					
					Intent i = new Intent(SearchActivity.this, ResultSearchActivity.class);
					i.putExtra("q_search", editText.getText().toString());
					startActivity(i);
//					ShowDialog.progressDialog(CTX, 5);
				}
				
			}
		});
		
	}

	protected boolean isValid(String string) {
		
		return true;
	}
}
	

