package com.library.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.library.android.domain.Book;
import com.library.android.mock.ConstantsMock;
import com.library.android.services.BookService;
import com.library.android.services.impl.BookServicesImpl;
import com.library.android.view.BookListItemView;

public class BookListActivity extends ListActivity {
	
	private BookService service;
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.book_list_view);
        
    }
    
    public void onStart(){
		super.onStart();

		BookListAsyncTask asyncTask = new BookListAsyncTask();
		service = BookServicesImpl.getInstance();
		asyncTask.execute();

	}

    
    public void onListItemClick(ListView parent, final View view, int position, long id){   
    	
    	
    	
    	int tam = parent.getChildCount();
    	int i = 0;
    	while(i < tam){
    		BookListItemView itemView = (BookListItemView)parent.getChildAt(i);

    		if(view.getId() != id){
    			
    			try {
					itemView.setBookPicture(BitmapFactory.decodeStream(getAssets().open(ConstantsMock.book1_picture)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    		i++;
    	}
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

	
	private class BookListAsyncTask extends AsyncTask<Void, Context, List<Book>> {

		private BookListItemView itemBook;
		
		@Override
		protected List<Book> doInBackground(Void... params) {
			
			return service.findAllBooks();
		}
		
		@Override
		protected void onPostExecute(List<Book> results){
				itemBook = (BookListItemView) findViewById(R.layout.book_list_view);
				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
							
				if(results.size() > 0){
					for(Book aBook : results){
						HashMap<String,String> temp = new HashMap<String,String>();
//						temp.put("picture", aBook.getPicture());
						temp.put("title", aBook.getTitle());
						temp.put("author", aBook.getAuthor());
						temp.put("score", aBook.getScore().toString());
						temp.put("comments", String.valueOf(aBook.getListOfComments().size()));
						list.add(temp);
					}
				
				
				SimpleAdapter simpleAdapter = new SimpleAdapter(BookListActivity.this, list, R.layout.item_book_list, new String[] { "title", "author", "score", "comments"},
				        new int[] { R.id.item_title_book, R.id.item_author_book, R.id.item_score_book, R.id.item_comments_book});
		        		setListAdapter(simpleAdapter);
				}
//				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListaAmigosActivity.this,
//				        R.layout.amigos, R.id.text_item, myStrings);
//
//				setListAdapter(arrayAdapter);

		
		}

    }
}