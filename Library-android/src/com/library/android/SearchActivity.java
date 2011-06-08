package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity {
	
	private EditText editText;
	private Button searchButton;

	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.search_book);
		
		editText = (EditText)findViewById(R.id.edit_buscar);
		searchButton = (Button) findViewById(R.id.search_button);
		
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				if(isValid(text)){
					
					//ALGO
					
				}
				
			}
		});
		
	}

	protected boolean isValid(String string) {
		
		return true;
	}
	
}
