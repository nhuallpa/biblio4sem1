package com.library.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.library.android.config.ConfigurationManager;
import com.library.android.dto.User;
import com.library.android.services.impl.UserServicesImpl;


/**
 * API KEY :ABQIAAAAciM87ZDsHBpWSo69gghBUhTBaO2NnPIX2ODBAYKWUTh_GfwcSRRzx5s0cMioil3poeutRThJRI35IQ
 * @author gonzalo.martin
 *
 */


public class UserProfileActivity extends Activity {
	
	private TextView username;
	private TextView phone;
	private TextView email;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile);
		username = (TextView) findViewById(R.id.profile_username);

		phone = (TextView) findViewById(R.id.profile_phone);
		email = (TextView) findViewById(R.id.profile_email);
		
		init();
	}

	
	private void init() {
		User user = ConfigurationManager.getInstance(getApplicationContext()).getCurrentUser();
		username.setText(user.getName());
		email.setText(user.getEmail());
		
	}


	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_user_profile, menu);
        return true;
    }
	
	public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()){

        	case R.id.menu_my_comments: {
        		Intent i = new Intent(UserProfileActivity.this, MyCommentsActivity.class);
        		startActivity(i);
        	}break;
        	
        	case R.id.menu_my_reservations: {
        		Intent i = new Intent(UserProfileActivity.this, MyReservationsActivity.class);
        		startActivity(i);
        	}break;
        	
        	case R.id.menu_my_score: {
        		ConfigurationManager config = ConfigurationManager.getInstance(getApplicationContext());
        		if(!config.checkNetwork().equals("OK")){
        			config.showErrorNetwork();
        		} else {
        			User user = ConfigurationManager.getInstance(getApplicationContext()).getCurrentUser();
            		AlertDialog.Builder builder = new AlertDialog.Builder(this);
            		builder.setTitle("Mi Puntaje");
            		int myScore = UserServicesImpl.getInstance(getApplicationContext()).getMyScore(user.getId());
            		builder.setMessage("Puntaje: " + myScore);
            		builder.setNeutralButton("Ok", new OnClickListener() {
    					
    					@Override
    					public void onClick(DialogInterface dialog, int which) {
    						dialog.dismiss();
    					}
    				});
            		builder.show();
        		}
        		
        	}break;
        	
        	case R.id.menu_my_awards: {
        		Intent i = new Intent(UserProfileActivity.this, MyAwardsActivity.class);
        		startActivity(i);
        	}break;

        }
        
        return true;
        }


	

}
