package com.example.android.canvsdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class BoardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.board));

        FloatingActionButton fButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();



        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView fillIcon = new ImageView(this);
        fillIcon.setImageDrawable( getResources().getDrawable(R.drawable.home));

        //SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        ImageView paint1Icon = new ImageView(this);
        paint1Icon.setImageDrawable( getResources().getDrawable(R.drawable.brush2));

        //SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView pencilIcon = new ImageView(this);
        pencilIcon.setImageDrawable( getResources().getDrawable(R.drawable.message));

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
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.MainActivity"));
            }
        });*/
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.PaintActivity"));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.DiaryActivity"));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_board, menu);
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
