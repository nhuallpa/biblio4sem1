package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * API KEY :ABQIAAAAciM87ZDsHBpWSo69gghBUhTBaO2NnPIX2ODBAYKWUTh_GfwcSRRzx5s0cMioil3poeutRThJRI35IQ
 * @author gonzalo.martin
 *
 */


public class UserProfileActivity extends Activity {
	
	private double longitude;
	private double latitude;
	private Button showMapButton;
	private TextView tv_latitude;
	private TextView tv_longitude;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile);
		
		
		tv_latitude = (TextView) findViewById(R.id.text_latitud);
		tv_longitude = (TextView) findViewById(R.id.text_longitud);
		showMapButton = (Button) findViewById(R.id.show_map_button);
		
		setValues();
		
		showMapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String lat = String.valueOf(latitude);
				String lon = String.valueOf(longitude);
//				Uri name = Uri.parse("geo:lat,lon?q=name");
				String name = "now!";
//				initLocation();
				setValues();
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("geo:" + lat + "," + lon + "?q=" + name));
//						Uri.parse("geo:0,0?q=" + lat + "," + lon + " (" + name + ")"));
						startActivity(intent);

			}
		});
		
		
		
	}
	
	private void setValues(){
		tv_latitude.setText(String.valueOf(longitude));
		tv_longitude.setText(String.valueOf(latitude));
	}

//	private void initLocation() {
//
//		LocationManager mlocManager =
//
//			(LocationManager)getSystemService(Context.LOCATION_SERVICE);
//
//			LocationListener mlocListener = new MyLocationListener(this);
//
//			mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
//
//	}
	
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

        	}break;

        }
        
        return true;
        }


	

}
