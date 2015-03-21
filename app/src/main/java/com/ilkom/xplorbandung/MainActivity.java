package com.ilkom.xplorbandung;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends ActionBarActivity {

    MyItemizedOverlay myItemizedOverlay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = new MapView(this, 256);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        setContentView(mapView);
        mapView.getController().setZoom(15);
        mapView.getController().setCenter(new GeoPoint(-6.918477, 107.6093402));
        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        mapView.setUseDataConnection(false);
        mapView.setMaxZoomLevel(18);
        mapView.setMinZoomLevel(13);

        /*
        *  Set the limit view of map
        * */

        double boundNorth = -6.836443;
        double boundSouth = -6.971412;
        double boundWest = 107.549286;
        double boundEast = 107.744293;
        BoundingBoxE6 boxE6 = new BoundingBoxE6(boundNorth, boundEast, boundSouth, boundWest);
        mapView.setScrollableAreaLimit(boxE6);

        /*
        *  Set the pointer of User's Location
        * */

        CheckLocation checkLocation = new CheckLocation();
        checkLocation.getLocation();

        Drawable marker = getResources().getDrawable(android.R.drawable.star_big_on);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);

        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());

        myItemizedOverlay = new MyItemizedOverlay(marker, resourceProxy);
        mapView.getOverlays().add(myItemizedOverlay);

        GeoPoint myPoint = new GeoPoint(checkLocation.getLatitude(),checkLocation.getLongitude());
        myItemizedOverlay.addItem(myPoint, "myPoint", "myPoint");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
