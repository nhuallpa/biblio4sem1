package com.library.android;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.library.android.map.HelloItemizedOverlay;

	
/**api key: 06VodRsRvFxte97Zhz01LJle3sFuSYTO0MQqymQ **/

public class ShowMapActivity extends MapActivity {
	private MapView mapView;
	
	public void onCreate(Bundle bundle) {
	    super.onCreate(bundle);
	    setContentView(R.layout.library_map); // bind the layout to the activity
	    mapView = (MapView) findViewById(R.id.map_view);
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
	    mapView.setBuiltInZoomControls(true);
	    
	    GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
	    
	    GeoPoint point2 = new GeoPoint(35410000, 139460000);
	    OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
	    
	    itemizedoverlay.addOverlay(overlayitem);
//	    itemizedoverlay.addOverlay(overlayitem2);
	    
	    mapOverlays.add(itemizedoverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}

	

}
