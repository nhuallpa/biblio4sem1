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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	public static String NAME_DATA = "name";
	public static String PASS_DATA = "pass";
			
	private EditText nameText;
	private EditText passText;
	private Button loginButton;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		nameText = (EditText) findViewById(R.id.name_login);
		passText = (EditText) findViewById(R.id.pass_login);
		loginButton = (Button) findViewById(R.id.login_button);
		
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isWrong()){
					Toast.makeText(LoginActivity.this, "Invalid input...", Toast.LENGTH_SHORT);
				} else {
					String name = nameText.getText().toString();
					String pass = passText.getText().toString();
					Toast.makeText(LoginActivity.this, "Correct Input!", Toast.LENGTH_SHORT);
					Intent i = new Intent(LoginActivity.this, MainActivity.class);
					i.putExtra(NAME_DATA, name);
					i.putExtra(PASS_DATA, pass);
					startActivity(i);
				}
				
			}
		});
		
	}
	
	 public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        
	        inflater.inflate(R.menu.menu, menu);
	        return true;
	    }
	    
	    public boolean onOptionsItemSelected (MenuItem item){

	        switch (item.getItemId()){

	        case R.id.profile : {
	        	Intent i = new Intent(LoginActivity.this, ProfileUserActivity.class);
	        	startActivity(i);
	        } break;
	        
	        case R.id.about: {} break;
	        
	        }
	        
	       return true;
	    }
	
	private boolean isWrong(){
		
		//valida con el webservice
		String name = this.nameText.getText().toString();
		String pass = this.passText.getText().toString();
		
		return name.equals("") && pass.equals("");
	}

}
