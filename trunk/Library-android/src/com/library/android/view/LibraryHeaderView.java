package com.library.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.android.R;

public class LibraryHeaderView extends RelativeLayout {
	
	private TextView headerTitle;
	private Button librarysButton;
	private Button bookStoreButton;
	
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
		headerTitle = (TextView) findViewById(R.id.header_title);
		librarysButton = (Button) findViewById(R.id.header_librarys_button);
		bookStoreButton = (Button) findViewById(R.id.header_book_store_button);
		
		librarysButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Show Library Map", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		bookStoreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Show Book Store", Toast.LENGTH_SHORT).show();
				
			}
		});

	}
	
	public void setHeaderTitle(String text){
		this.headerTitle.setText(text);
	}
	
	public String getHeaderTitle(){
		return this.headerTitle.toString();
	}

}
