package com.library.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ToReserveBookActivity extends Activity {
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.to_reserve_book);
	}

}
