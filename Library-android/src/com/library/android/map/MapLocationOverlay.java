package com.library.android.map;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapLocationOverlay extends ItemizedOverlay<OverlayItem> {
	
//	// Store these as global instances so we don't keep reloading every time
////	private Bitmap bubbleIcon, shadowIcon;
//
//	private ShowMapView mapLocationViewer;
//
//	private Paint innerPaint, borderPaint, textPaint;
//
//	// The currently selected Map Location...if any is selected. This tracks
//	// whether an information
//	// window should be displayed & where...i.e. whether a user 'clicked' on a
//	// known map location
//	private MapLocation selectedMapLocation;
//
//	public MapLocationOverlay(ShowMapView mapLocationViewer) {
//
//		this.mapLocationViewer = mapLocationViewer;
////
////		bubbleIcon = BitmapFactory.decodeResource(mapLocationViewer.getResources(), R.drawable.banelco);
////		shadowIcon = BitmapFactory.decodeResource(mapLocationViewer.getResources(), R.drawable.shadow);
//
//	}
//
//	@Override
//	public boolean onTap(GeoPoint p, MapView mapView) {
//
//		// Store whether prior popup was displayed so we can call invalidate() &
//		// remove it if necessary.
//		boolean isRemovePriorPopup = selectedMapLocation != null;
//
//		// Next test whether a new popup should be displayed
//		selectedMapLocation = getHitMapLocation(mapView, p);
//		if (isRemovePriorPopup || selectedMapLocation != null) {
//			mapView.invalidate();
//		}
//
//		// Lastly return true if we handled this onTap()
//		return selectedMapLocation != null;
//	}
//
//	@Override
//	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
//
//		drawMapLocations(canvas, mapView, shadow);
////		drawInfoWindow(canvas, mapView, shadow);
//	}
//
//	/**
//	 * Test whether an information balloon should be displayed or a prior
//	 * balloon hidden.
//	 */
//	private MapLocation getHitMapLocation(MapView mapView, GeoPoint tapPoint) {
//
//		// Track which MapLocation was hit...if any
//		MapLocation hitMapLocation = null;
//
//		RectF hitTestRecr = new RectF();
//		Point screenCoords = new Point();
//		Iterator<MapLocation> iterator = mapLocationViewer.getMapLocations()
//				.iterator();
//		while (iterator.hasNext()) {
//			MapLocation testLocation = iterator.next();
//
//			// Translate the MapLocation's lat/long coordinates to screen
//			// coordinates
//			mapView.getProjection().toPixels(testLocation.getPoint(),
//					screenCoords);
//
//			// Create a 'hit' testing Rectangle w/size and coordinates of our
//			// icon
//			// Set the 'hit' testing Rectangle with the size and coordinates of
//			// our on screen icon
////			hitTestRecr.set(-bubbleIcon.getWidth() / 2,
////					-bubbleIcon.getHeight(), bubbleIcon.getWidth() / 2, 0);
//			hitTestRecr.offset(screenCoords.x, screenCoords.y);
//
//			// Finally test for a match between our 'hit' Rectangle and the
//			// location clicked by the user
//			mapView.getProjection().toPixels(tapPoint, screenCoords);
//			if (hitTestRecr.contains(screenCoords.x, screenCoords.y)) {
//				hitMapLocation = testLocation;
//				break;
//			}
//		}
//
//		// Lastly clear the newMouseSelection as it has now been processed
//		tapPoint = null;
//
//		return hitMapLocation;
//	}
//
//	private void drawMapLocations(Canvas canvas, MapView mapView, boolean shadow) {
//
//		Iterator<MapLocation> iterator = mapLocationViewer.getMapLocations()
//				.iterator();
//		Point screenCoords = new Point();
//		while (iterator.hasNext()) {
//			MapLocation location = iterator.next();
//			mapView.getProjection().toPixels(location.getPoint(), screenCoords);
//
////			if (shadow) {
////				// Only offset the shadow in the y-axis as the shadow is angled
////				// so the base is at x=0;
////				canvas.drawBitmap(shadowIcon, screenCoords.x, screenCoords.y
////						- shadowIcon.getHeight(), null);
////			} 
////			else {
////
////				// Cambiar las marcas.
////				bubbleIcon = getCustomMark(location.getName().split("\\|")[0]);
////				canvas.drawBitmap(bubbleIcon, screenCoords.x
////						- bubbleIcon.getWidth() / 2, screenCoords.y
////						- bubbleIcon.getHeight(), null);
////			}
//		}
//	}
//
////	private Bitmap getCustomMark(String custom) {
////
////		Bitmap result = BitmapFactory.decodeResource(mapLocationViewer
////				.getResources(), R.drawable.banelco);
////
////		if (custom.equalsIgnoreCase("Aqui estoy"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.ubact);
////		
////		if (custom.equalsIgnoreCase("Banco Comafi"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.comafi);
////
////		if (custom.equalsIgnoreCase("Banco de Servicios Financieros"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.financiero);
////
////		if (custom.equalsIgnoreCase("Banco del Sol"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.sol);
////
////		if (custom.equalsIgnoreCase("Banco Galicia"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.galicia);
////
////		if (custom.equalsIgnoreCase("Banco Itaú"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.itau);
////
////		if (custom.equalsIgnoreCase("Banco Macro"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.macro);
////
////		if (custom.equalsIgnoreCase("Banco Patagonia"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.patagonia);
////
////		if (custom.equalsIgnoreCase("Banco Regional de Cuyo"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.regional);
////
////		if (custom.equalsIgnoreCase("Banco Santander Río"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.santander);
////
////		if (custom.equalsIgnoreCase("Banco Supervielle"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.supervielle);
////
////		if (custom.equalsIgnoreCase("Banco Tucumán"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.macro);
////
////		if (custom.equalsIgnoreCase("BBVA Banco Francés"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.bbva);
////
////		if (custom.equalsIgnoreCase("Citibank"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.citi);
////
////		if (custom.equalsIgnoreCase("Credilogros"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.credilogros);
////
////		if (custom.equalsIgnoreCase("HSBC"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.hsbc);
////
////		if (custom.equalsIgnoreCase("Standard Bank"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.standard);
////
////		if (custom.equalsIgnoreCase("Supervielle Centro Banex"))
////			result = BitmapFactory.decodeResource(mapLocationViewer
////					.getResources(), R.drawable.supervielle);
////
////		return result;
////	}
//
////	private void drawInfoWindow(Canvas canvas, MapView mapView, boolean shadow) {
////
////		if (selectedMapLocation != null) {
////			if (shadow) {
////				// Skip painting a shadow in this tutorial
////			} else {
////
////				String[] lineas = selectedMapLocation.getName().split("\\|");
////
////				if (lineas[0] == "Banco Galicia")
////					bubbleIcon = BitmapFactory.decodeResource(mapLocationViewer
////							.getResources(), R.drawable.galicia);
////
////				int imax_width = 0;
////				for (String linea : lineas) {
////					if (linea.length() > imax_width)
////						imax_width = linea.length();
////				}
////
////				// First determine the screen coordinates of the selected
////				// MapLocation
////				Point selDestinationOffset = new Point();
////				mapView.getProjection().toPixels(
////						selectedMapLocation.getPoint(), selDestinationOffset);
////
////				// Setup the info window with the right size & location
////				// int INFO_WINDOW_WIDTH = 125;
////				int INFO_WINDOW_WIDTH = imax_width * 8;
////				// int INFO_WINDOW_HEIGHT = 25;
////				int INFO_WINDOW_HEIGHT = 70;
////				RectF infoWindowRect = new RectF(0, 0, INFO_WINDOW_WIDTH,
////						INFO_WINDOW_HEIGHT);
////				int infoWindowOffsetX = selDestinationOffset.x
////						- INFO_WINDOW_WIDTH / 2;
////				int infoWindowOffsetY = selDestinationOffset.y
////						- INFO_WINDOW_HEIGHT - bubbleIcon.getHeight();
////				infoWindowRect.offset(infoWindowOffsetX, infoWindowOffsetY);
////
////				// Draw inner info window
////				canvas.drawRoundRect(infoWindowRect, 5, 5, getInnerPaint());
////
////				// Draw border for info window
////				canvas.drawRoundRect(infoWindowRect, 5, 5, getBorderPaint());
////
////				// Draw the MapLocation's name
////				int TEXT_OFFSET_X = 10;
////				int TEXT_OFFSET_Y = 23;
////
////				for (String linea : lineas) {
////					canvas.drawText(linea, infoWindowOffsetX + TEXT_OFFSET_X,
////							infoWindowOffsetY + TEXT_OFFSET_Y, getTextPaint());
////					TEXT_OFFSET_Y += 16;
////				}
////
////			}
////		}
////	}
//
//	public Paint getInnerPaint() {
//		if (innerPaint == null) {
//			innerPaint = new Paint();
//			innerPaint.setARGB(225, 75, 75, 75); // gray
//			innerPaint.setAntiAlias(true);
//		}
//		return innerPaint;
//	}
//
//	public Paint getBorderPaint() {
//		if (borderPaint == null) {
//			borderPaint = new Paint();
//			borderPaint.setARGB(255, 255, 255, 255);
//			borderPaint.setAntiAlias(true);
//			borderPaint.setStyle(Style.STROKE);
//			borderPaint.setStrokeWidth(2);
//		}
//		return borderPaint;
//	}
//
//	public Paint getTextPaint() {
//		if (textPaint == null) {
//			textPaint = new Paint();
//			textPaint.setARGB(255, 255, 255, 255);
//			textPaint.setAntiAlias(true);
//		}
//		return textPaint;
//	}
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	public MapLocationOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public MapLocationOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}
	

	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}

}
