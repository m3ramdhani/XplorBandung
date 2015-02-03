package com.ilkom.xplorbandung;

import android.graphics.Point;
import android.graphics.drawable.Drawable;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * Created by MohamadTri on 03/02/2015.
 */
public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {

    private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();

    public MyItemizedOverlay(Drawable pDefaultMarker, ResourceProxy pResourceProxy) {
        super(pDefaultMarker, pResourceProxy);
    }

    @Override
    protected OverlayItem createItem(int i) {
        return overlayItemList.get(i);
    }

    @Override
    public int size() {
        return overlayItemList.size();
    }

    @Override
    public boolean onSnapToItem(int i, int i2, Point point, IMapView iMapView) {
        return false;
    }

    public void addItem(GeoPoint p, String title, String snippet) {
        OverlayItem newItem = new OverlayItem(title, snippet, p);
        overlayItemList.add(newItem);
        populate();
    }
}
