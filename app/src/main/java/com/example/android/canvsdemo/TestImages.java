package com.example.android.canvsdemo;

/**
 * Created by kingHenry on 4/18/15.
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class TestImages extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ImageView image = (ImageView) findViewById(R.id.ChoosenImageView);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.earth);
        image.setImageBitmap(bMap);


    }
}