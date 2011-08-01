package com.library.android.services.impl;

import java.util.List;

import com.library.android.domain.Book;
import com.library.android.domain.Comment;
import com.library.android.domain.Reservation;
import com.library.android.mock.LibraryMocks;
import com.library.android.services.BookService;
import com.library.android.services.ConfigWS;

public class BookServicesImpl implements BookService {
	
	private static String url;
	
	private static BookServicesImpl instance;
	
	private BookServicesImpl(){
		ConfigWS config = ConfigWS.getInstance();
		url = ConfigWS.WS_SEARCH + "mail=" + config.getUser();
	}
	
	public static BookServicesImpl getInstance(){
		if(instance == null){
			instance = new BookServicesImpl();
		}
		return instance;
	}
	
	@Override
	public List<Book> findBook(String text){
		
//		String urlFinal = url + "&bookId=" + text;
//		String request = null;
//		
//		JSONArray array = null;
//		JSONObject bookJs = null;
//		Book book = new Book();
//		try {
//			URL u = new URL(urlFinal);
//			HttpURLConnection con = (HttpURLConnection) u.openConnection ();
//			con.setDoInput(true);
//			con.setRequestMethod("GET");
//			con.connect();
//			request = con.getResponseMessage();
//			if(request.equals("OK")){
//				
//				InputStream inputStream = con.getInputStream();
//					
//				// Parse it line by line
//			    BufferedReader bufferedReader = new BufferedReader(
//			                new InputStreamReader(inputStream));
//			    StringBuffer sb = new StringBuffer();
//
//			    String str = "";
//			    while ((str = bufferedReader.readLine()) != null) {
//			     sb.append(str);
//			    }
//			    array = new JSONArray(sb.toString());
//				bookJs = array.getJSONObject(1);
//				book.setBookId(bookJs.getLong("bookId"));
//				book.setTitle(bookJs.getString("title"));
//					
//				con.disconnect();
//			}
//			//array = new JSONArray(request);
//			
//				
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//					
				
		List<Book> booksFounded = null;//LibraryServiceImpl.getInstance().booksFounded(BooksMock.getListBooks(), text);
		
//		return book;
		return booksFounded;
	}
	
	public void addBook(String aRequest){
		
	}
	
	public boolean validate(Long bookId){
		
		return false;
	}
	
	public void toComment(Long bookId, Comment aComment){
		//TODO: implements comunication with WS
	}
	
	public void toReserveBook(Long bookId, Reservation aReservation){
		
	}

	@Override
	public List<Book> getTopBooks() {
		
		return LibraryMocks.getInstance().getAllBooks();
	}


}
