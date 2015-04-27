package com.example.android.canvsdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.UUID;

/**
 * Created by minHenry on 4/15/15.
 */
public class PaintActivity extends Activity implements View.OnClickListener {
    private DrawingView drawView;

    private float smallBrush, mediumBrush, largeBrush;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn;
    private SubActionButton button1, button2, button3, button4, buttonPaint, buttonColor, buttonNew, buttonNote, person1button,person2button,person3button,person4button;
    private FloatingActionButton fButton, bottomLeftButton, topRightButton;
    private LinearLayout paintLayout;
    private SlidingUpPanelLayout mLayout;
    private static final String TAG = "SlidingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout_paint);
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        mLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            ImageView marsImg=(ImageView)findViewById(R.id.marsimg2);
            ImageView jupImg=(ImageView)findViewById(R.id.jupimg2);
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");
                marsImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaintActivity.this, PaintActivity.class);

                        intent.putExtra("methodName","drawOnMars");
                        startActivity(intent);
                    }
                });
                jupImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent jupIntent = new Intent(PaintActivity.this, PaintActivity.class);
                        jupIntent.putExtra("methodName","drawOnJupiter");
                        startActivity(jupIntent);
                    }
                });
            }


            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }
        });


        drawView = (DrawingView)findViewById(R.id.drawing);

//        paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
  //      currPaint = (ImageButton)paintLayout.getChildAt(0);
    //    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.brush2));

        FloatingActionButton fButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();



        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView fillIcon = new ImageView(this);
        fillIcon.setImageDrawable( getResources().getDrawable(R.drawable.message));

        //SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        ImageView paint1Icon = new ImageView(this);
        paint1Icon.setImageDrawable( getResources().getDrawable(R.drawable.brush2));

        //SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView pencilIcon = new ImageView(this);
        pencilIcon.setImageDrawable( getResources().getDrawable(R.drawable.board));

        ImageView calendarIcon = new ImageView(this);
        calendarIcon.setImageDrawable(getResources().getDrawable(R.drawable.calendar));


        SubActionButton button1 = itemBuilder.setContentView(fillIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(paint1Icon).build();
        SubActionButton button3 = itemBuilder.setContentView(pencilIcon).build();
        SubActionButton buttonCal = itemBuilder.setContentView(calendarIcon).build();

        FloatingActionMenu actionMenuBottomRight = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(buttonCal)
                .attachTo(fButton)
                .build();

///
        ImageView fabContent = new ImageView(this);
        fabContent.setImageDrawable(getResources().getDrawable(R.drawable.rightmenu));

        ImageView iconTRMenu = new ImageView(this); // Create an icon
        iconTRMenu.setImageDrawable(getResources().getDrawable(R.drawable.rightmenu));
        FloatingActionButton darkButton = new FloatingActionButton.Builder(this)
                //.setTheme(FloatingActionButton.THEME_DARK)
                .setContentView(iconTRMenu)
                .setPosition(FloatingActionButton.POSITION_TOP_RIGHT)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this)
                .setTheme(SubActionButton.THEME_DARK);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        //ImageView rlIcon4 = new ImageView(this);
        //ImageView rlIcon5 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.home));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.person));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.list));



        // Set 3 SubActionButtons
        FloatingActionMenu actionMenuTopRight = new FloatingActionMenu.Builder(this)
                .setStartAngle(180)
                .setEndAngle(90)

                        //.setAnimationHandler(new SlideDownAnimationHandler())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                        //.addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                        //.addSubActionView(rLSubBuilder.setContentView(rlIcon5).build())
                .attachTo(darkButton)
                .build();

        ////////

        ImageView bottomLeftIcon = new ImageView(this); // Create an icon
        bottomLeftIcon.setImageDrawable(getResources().getDrawable(R.drawable.autofix));

        bottomLeftButton = new FloatingActionButton.Builder(this)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_LEFT)
                .setContentView(bottomLeftIcon)
                .build();

        bottomLeftButton.setOnClickListener(this);

        SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView paintIcon = new ImageView(this);
        paintIcon.setImageDrawable(getResources().getDrawable(R.drawable.brush2));

        //SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        ImageView colorIcon = new ImageView(this);
        colorIcon.setImageDrawable(getResources().getDrawable(R.drawable.palette));

        //SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView newThingIcon = new ImageView(this);
        newThingIcon.setImageDrawable(getResources().getDrawable(R.drawable.eraser));

        ImageView noteIcon = new ImageView(this);
        noteIcon.setImageDrawable(getResources().getDrawable(R.drawable.addnote));

        buttonPaint = itemBuilder3.setContentView(paintIcon).build();
        buttonColor = itemBuilder3.setContentView(colorIcon).build();
        buttonNew = itemBuilder3.setContentView(newThingIcon).build();
        buttonNote = itemBuilder3.setContentView(noteIcon).build();
/*
        buttonPaint.setOnClickListener(this);
        buttonColor.setOnClickListener(this);
        buttonNew.setOnClickListener(this);
        buttonNote.setOnClickListener(this);
*/
        FloatingActionMenu actionMenuBottomLeft = new FloatingActionMenu.Builder(this)
                .setStartAngle(270)
                .setEndAngle(360)
                .addSubActionView(buttonPaint)
                .addSubActionView(buttonColor)
                .addSubActionView(buttonNew)
                .addSubActionView(buttonNote)
                .attachTo(bottomLeftButton)
                .build();



        ImageView topRightIcon = new ImageView(this); // Create an icon
        topRightIcon.setImageDrawable(getResources().getDrawable(R.drawable.person));

        topRightButton = new FloatingActionButton.Builder(this)
                .setPosition(FloatingActionButton.POSITION_TOP_LEFT)
                .setContentView(topRightIcon)
                .build();

        topRightButton.setOnClickListener(this);

        SubActionButton.Builder itemBuilder4 = new SubActionButton.Builder(this);
        ImageView person1Icon = new ImageView(this);
        person1Icon.setImageDrawable(getResources().getDrawable(R.drawable.person));

        //SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        ImageView person2Icon = new ImageView(this);
        person2Icon.setImageDrawable(getResources().getDrawable(R.drawable.person));

        //SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView person3Icon = new ImageView(this);
        person3Icon.setImageDrawable(getResources().getDrawable(R.drawable.person));

        ImageView person4icon = new ImageView(this);
        person4icon.setImageDrawable(getResources().getDrawable(R.drawable.person));

        person1button = itemBuilder4.setContentView(person1Icon).build();
        person2button = itemBuilder4.setContentView(person2Icon).build();
        person4button = itemBuilder4.setContentView(person4icon).build();
        person3button = itemBuilder4.setContentView(person3Icon).build();

        person1button.setOnClickListener(this);
        person2button.setOnClickListener(this);
        person4button.setOnClickListener(this);
        person3button.setOnClickListener(this);

        FloatingActionMenu actionMenuTOpLeft = new FloatingActionMenu.Builder(this)
                .setStartAngle(90)
                .setEndAngle(0)
                .addSubActionView(person1button)
                .addSubActionView(person2button)
                .addSubActionView(person4button)
                .addSubActionView(person3button)
                .attachTo(topRightButton)
                .build();

        getActionBar().hide();
        String condition=getIntent().getStringExtra("methodName");
        Handler handler = new Handler();
        if(condition.equals("drawOnJupiter")){
            handler.postDelayed(taskJupiter, 3000);
        }else if(condition.equals("drawOnMars")){
            handler.postDelayed(taskMars,3000);
        }
        else if (condition.equals("simple")){
            handler.postDelayed(taskSimple,2000);

        }else {
            Log.i("YO","YO");
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.DiaryActivity"));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.BoardActivity"));
            }
        });


    }

    private Runnable taskMars = new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_LONG).show();
            drawView.drawOnMars();
        }
    };
    private Runnable taskJupiter = new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_LONG).show();
            drawView.drawOnJupiter();
        }
    };
    private Runnable taskSimple = new Runnable() {
        public void run() {
            Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_LONG).show();
            drawView.startNew();
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_paint);
        drawView = (DrawingView)findViewById(R.id.drawing);

           }


    @Override
    public void onClick(View view){
//respond to clicks
        buttonColor.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.i("here","i came here");
                AlertDialog.Builder newDialog = new AlertDialog.Builder(PaintActivity.this);
                newDialog.setView(R.layout.colors_dialog);
                newDialog.setTitle("Choose a color");
                newDialog.setMessage("Pick a color");


           /* newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });*/

                newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        buttonPaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("here","i came here");
                //draw button clicked
                final Dialog brushDialog = new Dialog(PaintActivity.this);
                brushDialog.setTitle("Brush size:");
                brushDialog.setContentView(R.layout.brush_chooser);
                ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setBrushSize(smallBrush);
                        drawView.setLastBrushSize(smallBrush);
                        drawView.setErase(false);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i("here","i came here");
                        drawView.setBrushSize(mediumBrush);
                        drawView.setLastBrushSize(mediumBrush);
                        drawView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setBrushSize(largeBrush);
                        drawView.setLastBrushSize(largeBrush);
                        drawView.setErase(false);
                        brushDialog.dismiss();
                    }
                });
                brushDialog.show();
            }
        });
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("here","i came here");
                //switch to erase - choose size
                final Dialog brushDialog = new Dialog(PaintActivity.this);
                brushDialog.setTitle("Eraser size:");
                brushDialog.setContentView(R.layout.brush_chooser);
                ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setErase(true);
                        drawView.setBrushSize(smallBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setErase(true);
                        drawView.setBrushSize(mediumBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawView.setErase(true);
                        drawView.setBrushSize(largeBrush);
                        brushDialog.dismiss();
                    }
                });
                brushDialog.show();
            }
        });
        buttonNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("here","i came here");
                //new button
                //startActivity(new Intent("com.example.android.canvsdemo.Test"));

                AlertDialog.Builder newDialog = new AlertDialog.Builder(PaintActivity.this);
                newDialog.setTitle("New drawing");
                newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
                newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        drawView.startNew();
                        dialog.dismiss();
                    }
                });

                newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save drawing
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(PaintActivity.this);
                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device Gallery?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        //save drawing
                        drawView.setDrawingCacheEnabled(true);
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), drawView.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        drawView.destroyDrawingCache();
                    }
                });
                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                saveDialog.show();
            }
        });

    }


    public void paintClicked(View view){
        //use chosen color
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());
        if(view!=currPaint){
//update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getStringExtra("methodName").equals("drawOnJupiter")){
            drawView.drawOnJupiter();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paint, menu);
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

