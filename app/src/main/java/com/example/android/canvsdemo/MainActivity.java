package com.example.android.canvsdemo;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
//import com.getbase.floatingactionbutton.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,View.OnClickListener,
        CirclesActivity.OnFragmentInteractionListener, ToDoCards.OnFragmentInteractionListener{
    private static final String TAG = "SlidingActivity";
    private ImageButton createBtn;
    private SlidingUpPanelLayout mLayout;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CirclesActivity circlesActivity;
    private SubActionButton button1, button2, button3, button4, buttonPaint, buttonColor, buttonNew, buttonNote, person1button,person2button,person3button,person4button;
    private FloatingActionButton fButton, bottomLeftButton, topRightButton;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_panel);


        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.setPanelState(PanelState.HIDDEN);
        mLayout.setPanelSlideListener(new PanelSlideListener() {
            ImageView marsImg=(ImageView)findViewById(R.id.marsimg);
            ImageView jupImg=(ImageView)findViewById(R.id.jupimg);
          @Override
          public void onPanelSlide(View panel, float slideOffset) {
              Log.i(TAG, "onPanelSlide, offset " + slideOffset);
          }

          @Override
          public void onPanelExpanded(View panel) {
              Log.i(TAG, "onPanelExpanded");
              marsImg.setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(MainActivity.this, PaintActivity.class);

                      intent.putExtra("methodName","drawOnMars");
                      startActivity(intent);
                  }
              });
              jupImg.setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent jupIntent = new Intent(MainActivity.this, PaintActivity.class);
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


            mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        //circlesActivity = (CirclesActivity)
        //getFragmentManager().findFragmentById(R.id.circles1);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

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

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.home));

        FloatingActionButton fButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();



        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView fillIcon = new ImageView(this);
        fillIcon.setImageDrawable( getResources().getDrawable(R.drawable.message));

        //SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        ImageView paintIcon = new ImageView(this);
        paintIcon.setImageDrawable( getResources().getDrawable(R.drawable.brush2));

        //SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView pencilIcon = new ImageView(this);
        pencilIcon.setImageDrawable( getResources().getDrawable(R.drawable.board));

        ImageView calendarIcon = new ImageView(this);
        calendarIcon.setImageDrawable(getResources().getDrawable(R.drawable.calendar));


        SubActionButton button1 = itemBuilder.setContentView(fillIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(paintIcon).build();
        SubActionButton button3 = itemBuilder.setContentView(pencilIcon).build();
        SubActionButton buttonCal = itemBuilder.setContentView(calendarIcon).build();

        FloatingActionMenu actionMenuBottomRight = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(buttonCal)
                .attachTo(fButton)
                .build();

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createBtnClick();
            }
        });
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.DiaryActivity"));
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.android.canvsdemo.BoardActivity"));
            }
        });
        getActionBar().hide();




    }
    private void createBtnClick(){
        Intent paintIntent = new Intent(MainActivity.this, PaintActivity.class);
        paintIntent.putExtra("methodName","simple");
        startActivity(paintIntent);
    }

    @Override
    public void onClick(View view){

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.activity_circles, container, false);
                    break;
                case 2:
                   // rootView = inflater.inflate(R.layout.fragment_to_do_cards,container,false);
                    break;
                case 3:
                    //rootView = inflater.inflate(R.layout.fragment_obj_list, container, false);
                    break;

            }
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
        /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         * <p/>
         * See the Android Training lesson <a href=
         * "http://developer.android.com/training/basics/fragments/communicating.html"
         * >Communicating with Other Fragments</a> for more information.
         */



    }
    public void onFragmentInteraction(Uri uri){

    };

}
