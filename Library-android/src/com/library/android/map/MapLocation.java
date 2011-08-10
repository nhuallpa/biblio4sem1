package com.library.android.map;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class MapLocation {

	private GeoPoint _point;
	private OverlayItem overlayItem;
	
	public MapLocation(double latitude, double longitude) {
		_point = new GeoPoint((int)(latitude*1e6),(int)(longitude*1e6));
	}

	public GeoPoint getPoint() {
		return _point;
	}
	
	public void setDataOverlay(String title, String snippet){
		overlayItem = new OverlayItem(_point, title, snippet);
	}
	
	public OverlayItem getOverlayItem(){
		return this.overlayItem;
	}
	
}
