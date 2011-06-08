package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends Activity {
	
	private EditText editText;
	private ImageButton searchButton;
	private ImageButton topTenButton;
	private ImageButton luckyButton;

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
				// TODO Auto-generated method stub
				
			}
		});
		
		
		luckyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
	
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()){

        case R.id.profile : {
        	Intent i = new Intent(SearchActivity.this, ProfileUserActivity.class);
        	startActivity(i);
        } break;
        
        case R.id.login: {
        	Intent i = new Intent(SearchActivity.this, LoginActivity.class);
        	startActivity(i);
        } break;
        
        case R.id.about: {} break;
        
        }
        
       return true;
    }
	
}
