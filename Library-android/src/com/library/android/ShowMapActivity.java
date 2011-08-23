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
import com.library.android.domain.Library;
import com.library.android.map.MapLocation;
import com.library.android.map.MapLocationOverlay;
import com.library.android.mock.LibraryMocks;
import com.library.android.services.impl.LibraryServicesImpl;

	
/**api key: 06VodRsRvFxte97Zhz01LJle3sFuSYTO0MQqymQ **/

public class ShowMapActivity extends MapActivity {
	
//	private double minLat;
//	private double maxLat;
//	private double maxLong;
//	private double minLong;
	
	private MapView mapView;
//	private GeoLocation _gActual = new GeoLocation();
	
	public void onCreate(Bundle bundle) {
	    super.onCreate(bundle);
	    setContentView(R.layout.library_map); // bind the layout to the activity
	    mapView = (MapView) findViewById(R.id.map_view);
	    String libraryId = getIntent().getExtras().getString("libraryId");
	    if(libraryId == null){
	    	fillLocations();
	    } else {
	    	fillLibrary(libraryId);
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
		
		Library library = LibraryServicesImpl.getInstance().getLibrary(libraryId);
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
			
			
			MapLocationOverlay itemizedoverlay = new MapLocationOverlay(drawable, this);
			MapLocation mapLocation = new MapLocation(library.getLocation().getX(), library.getLocation().getY());
			mapLocation.setDataOverlay(library.getName(), library.getLocation().getAddress());
			itemizedoverlay.addOverlay(mapLocation.getOverlayItem());
			mapOverlays.add(itemizedoverlay);
			
		}

		
		
	}
	
//	-73.58168,-55.04528,-53.65028,-21.78751,Argentina
//	http://www.nearby.org.uk/coords/countries.csv
	
	private void fillLocations(){
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapView.setBuiltInZoomControls(true);
		mapView.setTraffic(true);
		
		//Load Map Librarys
		List<Library> libraries = LibraryMocks.getInstance().getLibraries();
		for(Library item : libraries){
			MapLocationOverlay itemizedoverlay = new MapLocationOverlay(drawable, this);
			MapLocation mapLocation = new MapLocation(item.getLocation().getX(), item.getLocation().getY());
			mapLocation.setDataOverlay(item.getName(), item.getLocation().getAddress());
			itemizedoverlay.addOverlay(mapLocation.getOverlayItem());
			mapOverlays.add(itemizedoverlay);
		}
		
	    
	   
	    
	    
//	    GeoPoint point = new GeoPoint((int)(-34.60375*1e6),(int)(-58.38152*1e6));
//	    MapLocation mapLocation = new MapLocation(-34.60375, -58.38152);
//	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "Obelisco!!");
//	    mapLocation.setDataOverlay("Hola, Mundo!", "Obelisco!!");
//	    itemizedoverlay.addOverlay(mapLocation.getOverlayItem());
//	    
	   
	}

}
