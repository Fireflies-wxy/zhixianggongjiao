package com.bnrc.bnrcbus.module.AR;

import com.baidu.location.BDLocation;

/**
 * Created by ntdat on 1/16/17.
 */

public class ARPoint {
    BDLocation location;
    String name;

    public ARPoint(String name, double lat, double lon, double altitude) {
        this.name = name;
        location = new BDLocation("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(altitude);
    }

    public BDLocation getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
