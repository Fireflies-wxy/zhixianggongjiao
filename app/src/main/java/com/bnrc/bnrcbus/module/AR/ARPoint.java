package com.bnrc.bnrcbus.module.AR;

import android.view.View;

import com.baidu.location.BDLocation;

/**
 * Created by ntdat on 1/16/17.
 */

public class ARPoint {
    private BDLocation location;
    private String name;
    private String distance;
    private View poiTag;

    public ARPoint(String name, String distance, double lat, double lon, double altitude) {
        this.name = name;
        this.distance = distance;
        location = new BDLocation("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(altitude);
    }

    public void setLocation(BDLocation location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(String distance) { this.distance = distance; }

    public void setPoiTag(View poiTag) { this.poiTag = poiTag; }

    public BDLocation getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getDistance() { return distance; }

    public View getPoiTag() {
        return poiTag;
    }
}
