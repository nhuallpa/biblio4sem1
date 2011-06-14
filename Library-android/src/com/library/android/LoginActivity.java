package com.library.android;


import java.io.IOException;

import com.library.android.services.ConfigWS;
import com.library.android.services.impl.UserServicesImpl;

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
	private ConfigWS config;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		config = ConfigWS.getInstance();
		
		nameText = (EditText) findViewById(R.id.name_login);
		passText = (EditText) findViewById(R.id.pass_login);
		loginButton = (Button) findViewById(R.id.login_button);
		
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isWrong()){
					Toast.makeText(LoginActivity.this, "Invalid input...", Toast.LENGTH_SHORT);
				} else {
					String mail = nameText.getText().toString();
					String pass = passText.getText().toString();
										
					try {
							String token = UserServicesImpl.login(mail, pass);
							if(token != null){
								config.setUser(mail);
								config.setToken(token);
								Intent i = new Intent(LoginActivity.this, SearchActivity.class);
								i.putExtra(NAME_DATA, mail);
								startActivity(i);
							} else {
								Toast.makeText(LoginActivity.this, "Error login", Toast.LENGTH_SHORT);
							}
							
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
					
					
				}
				
			}
		});
		
	}
	
	 
	
	private boolean isWrong(){
		
		
		String name = this.nameText.getText().toString();
		String pass = this.passText.getText().toString();
		
		return name.equals("") && pass.equals("");
	}

}
