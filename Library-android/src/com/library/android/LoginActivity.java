package com.library.android;


import java.io.IOException;

import android.app.Activity;
import android.content.Context;
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
	private String mail;
	private String pass;
	private Button loginButton;
	private ConfigWS config;
	private ConfigurationManager appConfig;
	private Context ctx = LoginActivity.this;
		
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		config = ConfigWS.getInstance();
		
		nameText = (EditText) findViewById(R.id.name_login);
		passText = (EditText) findViewById(R.id.pass_login);
		loginButton = (Button) findViewById(R.id.login_button);
		appConfig = ConfigurationManager.getInstance(this);
		Bundle extras = getIntent().getExtras();
		final Integer goToActivity = extras.getInt(Constants.GO_TO_ACTIVITY);
		final String bookId = extras.getString(Constants.BOOK_ID);
		final String bookName = extras.getString(Constants.BOOK_NAME);
		
		
		loginButton.setOnClickListener(new OnClickListener() {
			

			
			@Override
			public void onClick(View v) {
				mail = nameText.getText().toString();
				pass = passText.getText().toString();
				if(isWrong()){
					Toast.makeText(LoginActivity.this, "Invalid input...", Toast.LENGTH_SHORT).show();
				} else {
										
					try {
//							//BORRAR
//							fillData();
							
							String userId = UserServicesImpl.login(mail, pass, ctx);
							if(userId != null){
								saveConfig(userId);
								
								if(goToActivity != null){
									Class<?> classToGo = resolveClass(goToActivity);
									Intent i = new Intent(LoginActivity.this, classToGo);
									i.putExtra(Constants.BOOK_ID, bookId);
									i.putExtra(Constants.BOOK_NAME, bookName);
									startActivity(i);
								} else {
									Intent i = new Intent(LoginActivity.this, BookListActivity.class);
									startActivity(i);
								}
								Toast.makeText(LoginActivity.this, "You are logged successful!", Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(LoginActivity.this, "Error login", Toast.LENGTH_SHORT).show();
							}
							
						} catch (IOException e) {

						e.printStackTrace();
					}

				}
				
			}
			
			private Class<?> resolveClass(Integer cod){
				Class<?> classToGo = null;
				switch (cod) {
					case Constants.BOOK_DETAIL:{
						classToGo = BookDetailActivity.class;
					}break;
	
					case Constants.BOOK_LIST: {
						classToGo = BookListActivity.class;
					}break;
					
					case Constants.TO_COMMENT:{
						classToGo = ToCommentBookActivity.class;
					}
				}
				
				return classToGo;
			}
			
			private void saveConfig(String userId){
//				fillData();
				appConfig.setUserName(mail);
				appConfig.setPassword(pass);
				config.setUser(mail);
				appConfig.setUserId(userId);//cambiar
				
				appConfig.save();

			}

//			private void fillData() {
//				mail = "gonza";
//				pass = "gonza";
//			}
			
			
		});
		
	}
	
	

	 
	
	private boolean isWrong(){
		
		
		String name = this.nameText.getText().toString();
		String pass = this.passText.getText().toString();
		
		return name.equals("") && pass.equals("");
	}

}
