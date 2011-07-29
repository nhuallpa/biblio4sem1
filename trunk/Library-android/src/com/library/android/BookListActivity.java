package com.library.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.library.android.mock.LibraryMocks;
import com.library.android.view.BookListView;

public class BookListActivity extends Activity {

	private BookListView bookListView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.book_list_view);
        bookListView = (BookListView)findViewById(R.id.book_list);
        init();
        
    }
    
    private void init(){
    	bookListView.setBookList(LibraryMocks.getAllBooks());
    }
    
	 public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        
	        inflater.inflate(R.menu.menu_book_list, menu);
	        return true;
	    }
	    
	    public boolean onOptionsItemSelected (MenuItem item){

	        switch (item.getItemId()){

	        	case R.id.menu_search: {
	        		Intent i = new Intent(BookListActivity.this, SearchActivity.class);
	        		startActivity(i);
	        	}break;
	        
	            case R.id.menu_location: {
	            	
	            }break;

	            case R.id.menu_login:{
	            	Intent i = new Intent(BookListActivity.this, LoginActivity.class);
	            	startActivity(i);
	            	
	            }break;
	        }
	        
	        return true;
	        }
    
    public void onListItemClick(ListView parent, final View view, int position, long id){   
    	
    	
  
//    	
//    	view.setBackgroundResource(R.color.con_focus);
//    	nombreTv = (TextView) view.findViewById(R.id.name_mipost_amg);
//    	nombreTv.setTextColor(Color.WHITE);
//    	
//    	cuerpoTv = (TextView) view.findViewById(R.id.cuerpo_mipost_amg);
//    	cuerpoTv.setTextColor(Color.WHITE);
//    	
//    	postButton = (Button) view.findViewById(R.id.boton_post_amg);
//    	postButton.setVisibility(View.VISIBLE);
// 
//		postButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				TextView nomAmg = (TextView) view.findViewById(R.id.name_mipost_amg);
//				
//				//830061294656
//				DatoString nomAmigo = new DatoString();
//				nomAmigo.setDatoString(nomAmg.getText().toString());
//				
//				Bundle b = new Bundle();
//		        b.putParcelable("com.facebook.android.DatoJson", nomAmigo);
//				
//				Intent postear = new Intent(PostsAmigosActivity.this, PostearAAmigo.class);
//				postear.putExtras(b);
//				startActivity(postear);
//				
//			}
//		});
    	
    }


}