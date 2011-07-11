package com.library.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;
import com.library.android.services.ConfigWS;
import com.library.android.services.impl.UserServicesImpl;

public class LoginActivity extends Activity {
	
	private EditText nameText;
	private EditText passText;
	private Button loginButton;
	private ConfigWS config;
	private ConfigurationManager appConfig;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		config = ConfigWS.getInstance();
		
		nameText = (EditText) findViewById(R.id.name_login);
		passText = (EditText) findViewById(R.id.pass_login);
		loginButton = (Button) findViewById(R.id.login_button);
		appConfig = ConfigurationManager.getInstance(this);
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			String mail = nameText.getText().toString();
			String pass = passText.getText().toString();
			
			@Override
			public void onClick(View v) {
				if(isWrong()){
					Toast.makeText(LoginActivity.this, "Invalid input...", Toast.LENGTH_SHORT).show();
				} else {
										
//					try {
//							String token = UserServicesImpl.login(mail, pass);
							String token = UserServicesImpl.loginFake();
							if(token != null){
								config.setToken(token);
								saveConfig();
								Intent i = new Intent(LoginActivity.this, SearchActivity.class);
								i.putExtra(Constants.NAME_DATA, mail);
								startActivity(i);
							} else {
								Toast.makeText(LoginActivity.this, "Error login", Toast.LENGTH_SHORT).show();
							}
							
//						} catch (IOException e) {
//
//						e.printStackTrace();
//					}

				}
				
			}
			
			
			private void saveConfig(){
				fillData();
				appConfig.setUserName(mail);
				appConfig.setPassword(pass);
				config.setUser(mail);
				appConfig.save();
			}


			private void fillData() {
				this.mail = "lalosoft@gmail.com";
				this.pass = "123456";
			}
			
			
		});
		
	}
	
	

	 
	
	private boolean isWrong(){
		
		
		String name = this.nameText.getText().toString();
		String pass = this.passText.getText().toString();
		
		return name.equals("") && pass.equals("");
	}

}
