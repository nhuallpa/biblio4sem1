package com.library.android;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.services.ConfigWS;
import com.library.android.services.impl.BookServicesImpl;

public class SearchActivity extends Activity {
	
	private EditText editText;
	private ImageButton searchButton;
	private ImageButton topTenButton;
	private ImageButton luckyButton;
	private BookServicesImpl bookServices;
	
	private ConfigurationManager config;

	private Context CTX = SearchActivity.this; 
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_book);
		config = ConfigurationManager.getInstance(this);
		ConfigWS c = ConfigWS.getInstance();
//		c.setUser("lalosoft@gmail.com");
		
		bookServices = BookServicesImpl.getInstance();
		
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
				// TODO Auto-generated method stub
				
			}
		});
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				if(isValid(text)){
					//tendriamos que pasarle el intent con todos los datos de los libros
					
					//conexion
					//en principio, funciona si que se le pase nada
					//tendria que devolver una lista de libros
//					List<Book> listOfbooks = BookServicesImpl.findBook(editText.getText().toString());//pero tendria que ser asi
					
					Intent i = new Intent(SearchActivity.this, ResultSearchActivity.class);
					i.putExtra("inputText", editText.getText().toString());
//					i.putExtra("title", book.getTitle());
					startActivity(i);
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
	               
	        
	        case R.id.my_comments: {
	        	if(config.isLogged()){
		        	Intent i = new Intent(SearchActivity.this, MyCommentsActivity.class);
		        	startActivity(i);
	        	} else {
	        		Toast.makeText(CTX, "Not logged!!", Toast.LENGTH_SHORT).show();
	        		Intent i = new Intent(SearchActivity.this, LoginActivity.class);
	        		startActivity(i);
	        	}

	        }break;
        
        }
        
       return true;
    }
	
}
