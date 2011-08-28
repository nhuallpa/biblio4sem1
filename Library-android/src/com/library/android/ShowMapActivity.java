package com.library.android;

import java.io.IOException;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.library.android.dto.Library;
import com.library.android.map.MapLocation;
import com.library.android.map.MapLocationOverlay;
import com.library.android.services.impl.LibraryServicesImpl;

	
/**api map key: 06VodRsRvFxte97Zhz01LJle3sFuSYTO0MQqymQ -  GL**/

/**api map key: 06VodRsRvFxu5-1Rzm-h8QWjfa3U96phJvU26cw -  Notebook**/

public class ShowMapActivity extends MapActivity {
	
	private MapView mapView;
	
	public void onCreate(Bundle bundle) {
	    super.onCreate(bundle);
	    setContentView(R.layout.library_map); // bind the layout to the activity
	    mapView = (MapView) findViewById(R.id.map_view);
	    String libraryId = getIntent().getExtras().getString("libraryId"); 
	    if(libraryId.equals("0")){
	    	fillLocations();
	    } else {
	    	fillLibrary(getIntent().getExtras().getString("libraryId"));
	    }
	    
	}

	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}

	private void fillLibrary(String libraryId) {
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapView.setBuiltInZoomControls(true);
		mapView.setTraffic(true);
		
		Library library = setLocation(LibraryServicesImpl.getInstance().getLibrary(libraryId));
			
			MapLocationOverlay itemizedoverlay = new MapLocationOverlay(drawable, this);
			MapLocation mapLocation = new MapLocation(library.getLocation().getX(), library.getLocation().getY());
			mapLocation.setDataOverlay(library.getName(), library.getLocation().getAddress());
			itemizedoverlay.addOverlay(mapLocation.getOverlayItem());
			mapOverlays.add(itemizedoverlay);
		
	}
	
	private Library setLocation(Library library){
		Geocoder coder = new Geocoder(this);
		List<Address> addressList = null;
		try {
			String location = library.getLocation().getAddress() + ", " + 
								library.getLocation().getCity() + ", " +
								library.getLocation().getCountry();
			addressList = coder.getFromLocationName(location, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Address address = addressList.get(0);
		if(address.hasLatitude() && address.hasLongitude()){
			library.getLocation().setX(address.getLatitude());
			library.getLocation().setY(address.getLongitude());
		}
		return library;
	}
	
//	-73.58168,-55.04528,-53.65028,-21.78751,Argentina
//	http://www.nearby.org.uk/coords/countries.csv
	
	private void fillLocations(){
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapView.setBuiltInZoomControls(true);
		mapView.setTraffic(true);
		
		//Load Map Librarys
		List<Library> libraries = LibraryServicesImpl.getInstance().getLibrarys();
		for(Library item : libraries){
			
			Library libraryFinal = setLocation(item);
			
			MapLocationOverlay itemizedoverlay = new MapLocationOverlay(drawable, this);
			MapLocation mapLocation = new MapLocation(libraryFinal.getLocation().getX(), libraryFinal.getLocation().getY());
			mapLocation.setDataOverlay(libraryFinal.getName(), libraryFinal.getLocation().getAddress());
			itemizedoverlay.addOverlay(mapLocation.getOverlayItem());
			mapOverlays.add(itemizedoverlay);
		}
	   
	}

}
