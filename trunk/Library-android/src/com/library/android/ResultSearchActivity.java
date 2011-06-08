package com.library.android;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ResultSearchActivity extends ListActivity {
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.list_result_search);
	}
	
	public void onStart(){
		super.onStart();

		ResultAsyncTask d = new ResultAsyncTask();
		
		d.execute();

	}
	
	private class ResultAsyncTask extends AsyncTask<Void, Context, ArrayList<JSONObject>> {

		
		@Override
		protected ArrayList<JSONObject> doInBackground(Void... params) {
			
			Bundle extras = getIntent().getExtras();

			//DatoJson dato = extras.getParcelable("com.facebook.android.DatoJson");
				
			JSONArray jArray = null;
				
//				try {
//
//			//		jArray = dato.getDatoJson().getJSONArray("data");
//					
//				} catch (JSONException e1) {
//					
//					e1.printStackTrace();
//				}
			
			ArrayList<JSONObject> results = new ArrayList<JSONObject>();
			
			/**CARGO EL ARRAY**/
		
			for(int i = 0; i < jArray.length(); i++ ){
				
				try {
					
					results.add(jArray.getJSONObject(i));
					
				} catch (JSONException e) {
			
					e.printStackTrace();
				}
			}

			return results;
		}
		
		@Override
		protected void onPostExecute(ArrayList<JSONObject> results){

				ArrayList<String> myStrings = new ArrayList<String>();
				
				
				for(JSONObject obj : results){
					try {
						myStrings.add((String) obj.get("name"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
//				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListaAmigosActivity.this,
//				        R.layout.amigos, R.id.text_item, myStrings);
//
//				setListAdapter(arrayAdapter);

		
		}

    }

}
