package com.library.android.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.AwardsActivity;
import com.library.android.BookListActivity;
import com.library.android.R;
import com.library.android.ShowMapActivity;

public class LibraryHeaderView extends RelativeLayout {
	
	private ImageButton homeButton;
	private Button librarysButton;
	private Button awardsButton;
	private TextView textInfo;
	
	public LibraryHeaderView(Context context) {
		super(context);
		
	}
	
	/**
	 * Creates a SimpleTextField Widget with a defined Style.
	 * @param context
	 * @param attrs
	 */
	public LibraryHeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * Creates a SimpleTextField Widget with a defined Style and a defined style.
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public LibraryHeaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}


	/**
	 * This method initialize and inflate the resource xml that defines this widget.
	 * @param context
	 */
	private void init(final Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup inflated = (ViewGroup) inflater.inflate(R.layout.library_header, null);
		this.addView(inflated);
		homeButton = (ImageButton) findViewById(R.id.header_button_home);
		librarysButton = (Button) findViewById(R.id.header_librarys_button);
		awardsButton = (Button) findViewById(R.id.header_awards_button);
		textInfo = (TextView) findViewById(R.id.header_text);
		
		homeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, BookListActivity.class);
				context.startActivity(i);
			}
		});
		
		librarysButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Show Library Map", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(context, ShowMapActivity.class);
				i.putExtra("libraryId", "0");
				context.startActivity(i);
			}
		});
		awardsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Show Awards!!", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(context, AwardsActivity.class);
				context.startActivity(i);
			}
		});
	}
	
	
	public void setInfo(String text){
		this.textInfo.setText(text);
		this.textInfo.setVisibility(VISIBLE);
		this.textInfo.setTextColor(Color.BLACK);
	}
	
	public Button getLibraryButton(){
		return this.librarysButton;
	}
	
	

}
