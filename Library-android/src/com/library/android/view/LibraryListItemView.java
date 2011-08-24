package com.library.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.android.R;
import com.library.android.ShowMapActivity;
import com.library.android.dto.Library;

public class LibraryListItemView extends RelativeLayout {
	
	private TextView name;
	private TextView phone;
	private TextView email;
	private RelativeLayout relativeItem;
	private String libraryId;

	public LibraryListItemView(Context context) {
		super(context);
		inflate(context, R.layout.item_library_list, this);
		
		name = (TextView) findViewById(R.id.item_library_name);
		phone = (TextView) findViewById(R.id.item_library_phone);
		email = (TextView) findViewById(R.id.item_library_mail);
		relativeItem = (RelativeLayout) findViewById(R.id.item_library_relative_layout);
		
		relativeItem.setOnClickListener(myListener);
		
	}
	
	public void setItemLibrary(Library lib){
		name.setText(lib.getName());
		phone.setText(lib.getPhone());
		email.setText(lib.getEmail());
		libraryId = lib.getLibraryId();
	}
	
	private View.OnClickListener myListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i = new Intent(getContext(), ShowMapActivity.class);//libraryId
			i.putExtra("libraryId", libraryId);
			getContext().startActivity(i);
			
		}
	};

}
