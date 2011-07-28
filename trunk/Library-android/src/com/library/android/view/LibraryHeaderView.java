package com.library.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.android.R;

public class LibraryHeaderView extends RelativeLayout {
	
	private TextView headerTitle;
	
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
	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup inflated = (ViewGroup) inflater.inflate(R.layout.library_header, null);
		this.addView(inflated);
		headerTitle = (TextView) findViewById(R.id.header_title);
//		leftButton = (ImageButton) findViewById(R.id.left_button);
//		rightButton = (ImageButton) findViewById(R.id.right_button);
	}
	
	public void setHeaderTitle(String text){
		this.headerTitle.setText(text);
	}
	
	public String getHeaderTitle(){
		return this.headerTitle.toString();
	}

}
