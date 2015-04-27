package com.example.android.canvsdemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by minHenry on 4/15/15.
 */
public class DrawingView extends ViewGroup {

    //drawing path
    private Path drawPath;
    //ImageView choosenImageView;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    private Rect imgRect;
    //canvas bitmap
    private Bitmap canvasBitmap;
    private Bitmap bmp;
    private Bitmap mutableBitmap;
    private Bitmap newBitmap;
    private float brushSize, lastBrushSize;
    private boolean erase=false;
    private boolean theCanvas=false;



    private void setUpDrawing(){
        Resources res = getResources();
        Log.i("SETUP","OMG SO MUCH SETUP");
        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }
    public void startNew(){
        Log.i("NEW","SO NEWW");
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void drawOnJupiter(){
        //drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Resources res=getResources();
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inScaled=false;
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeResource(res, R.drawable.jupiter, bmpFactoryOptions);
        mutableBitmap=bmp.copy(Bitmap.Config.ARGB_8888, true);
        drawCanvas.drawBitmap(mutableBitmap,imgRect,imgRect,drawPaint);
        //canvasBitmap=mutableBitmap;
        theCanvas=true;
        invalidate();
    }
    public void drawOnMars(){
        //drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Resources res=getResources();
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inScaled=false;
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeResource(res, R.drawable.mars, bmpFactoryOptions);
        mutableBitmap=bmp.copy(Bitmap.Config.ARGB_8888, true);
        drawCanvas.drawBitmap(mutableBitmap,imgRect,imgRect,drawPaint);
        //canvasBitmap=mutableBitmap;
        theCanvas=true;
        invalidate();
    }

    public void setErase(boolean isErase){
//set erase true or false
        erase=isErase;
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);
    }
    public void setBrushSize(float newSize){
//update size
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//view given size
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("SIZEE", "THE ISZEE IS CHANGES");
        imgRect = new Rect(0,0,w*2,h*2);
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mutableBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//draw view
        if(theCanvas){
            canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
            canvas.drawPath(drawPath, drawPaint);
            }else {
            canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
            canvas.drawPath(drawPath, drawPaint);
            }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        Log.i("onlayout","hello shouldn't come heres");
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
//detect user touch

        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("onDown","hello onpen DOWN");
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.i("onMOVINGS","hello ondraw");
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("line","drew some shitty line");
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(String newColor){
//set color
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);


    }
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpDrawing();
        Log.i("someshintg","context1");

    }

    public DrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.i("someshintg","context2");

    }
    public DrawingView(Context context) {
        super(context);
        Log.i("someshintg","context3");
    }
}