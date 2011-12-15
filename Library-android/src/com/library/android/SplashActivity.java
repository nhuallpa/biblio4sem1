package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.library.android.config.ConfigurationManager;
import com.library.android.config.Constants;

public class SplashActivity extends Activity {

	public void onCreate(Bundle b){
		super.onCreate(b);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        
        
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
              ConfigurationManager config = ConfigurationManager.getInstance(getApplicationContext());
              if(config.checkNetwork().equals("OK")){
            	  Intent i = new Intent(SplashActivity.this, BookListActivity.class);
                  startActivity(i);
              } 
              finish();              
            }
        }, Constants.SPLASH_DISPLAY_LENGHT);
	}
}
