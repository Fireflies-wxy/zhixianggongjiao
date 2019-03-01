package com.bnrc.bnrcbus.module.AR;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.Matrix;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiResult;
import com.bnrc.bnrcbus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ntdat on 1/13/17.
 */

public class AROverlayView extends View {

    Context context;
    private float[] rotatedProjectionMatrix = new float[16];
    private BDLocation currentLocation;
    private List<ARPoint> arPoints;
    private FrameLayout mContainer;


    public AROverlayView(Context context) {
        super(context);

        this.context = context;

//        mContainer = findViewById(R.id.camera_container_layout);
//
//        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view  = inflater.inflate(R.layout.poitag_layout,null);
//
//        mContainer.addView(view);



//        POITag poiTag = new POITag(context);
//        poiTag.setX(500);
//        poiTag.setY(500);

        //Demo points
//        arPoints = new ArrayList<ARPoint>() {{
//            add(new ARPoint("KLPAC", 3.1850, 101.6868, 0));
//            add(new ARPoint("Twin Tower", 3.1579, 101.7116, 0));
//        }};
    }

    public void updatePoiResult(PoiResult poiResult){
        arPoints = new ArrayList<>();
        for(PoiInfo poi:poiResult.getAllPoi()){
            arPoints.add(new ARPoint(poi.getName(),poi.location.latitude,poi.location.longitude,0));
        }
        this.invalidate();
    }

    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
        this.invalidate();
    }

    public void updateCurrentLocation(BDLocation currentLocation){
        this.currentLocation = currentLocation;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentLocation == null || arPoints == null) {
            return;
        }

        final int radius = 30;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);

        Log.i("poiresultinfo", "onDraw invoked");

        for (int i = 0; i < arPoints.size(); i ++) {
            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            float[] cameraCoordinateVector = new float[4];
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            // cameraCoordinateVector[2] is z, that always less than 0 to display on right position
            // if z > 0, the point will display on the opposite
            if (cameraCoordinateVector[2] < 0) {
                float x  = (0.5f + cameraCoordinateVector[0]/cameraCoordinateVector[3]) * canvas.getWidth();
                float y = (0.5f - cameraCoordinateVector[1]/cameraCoordinateVector[3]) * canvas.getHeight();

//                Drawable d = null;
//
//                d = ContextCompat.getDrawable(context, R.drawable.poi_drawable);
//
//                Bitmap myBitmap = ((BitmapDrawable)d).getBitmap();
//
//
//                canvas.drawBitmap(myBitmap, x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
//                //canvas.drawCircle(x, y, radius, paint);
//                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);

            }
        }
    }
}
